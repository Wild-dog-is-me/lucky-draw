package org.dog.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.config.util.JwtUtil;
import org.dog.config.vo.FailInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: Odin
 * @Date: 2023/5/5 00:55
 * @Description:
 */

@Data
@Slf4j
@Component
// 从【application-dev-gateway.yml】配置文件中读取
@ConfigurationProperties(prefix = "ld.global-filter")
@Order(-100) // 值越小，优先级越高，即请求经过网关，首先执行过滤器
public class JwtTokenGlobalFilter implements GlobalFilter {

    private final ObjectMapper objectMapper;

    public JwtTokenGlobalFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 认证标识
     */
    private String AUTHORIZATION = "";

    /**
     * 忽略认证url
     */
    private Set<String> ignoreUrlSet = Set.of(
//            "user/login",
//            "user/register"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求url
        String url = exchange.getRequest().getURI().getPath();

        // 忽略认证
        for (String ignoreUrl : ignoreUrlSet) {
            if (Boolean.TRUE.equals(ignore(url, ignoreUrl))) {
                return chain.filter(exchange);
            }
        }

        String token = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
        ServerHttpResponse resp = exchange.getResponse();

        try {
            Map<String, Object> userMap = JwtUtil.verifyToken(token);
            ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
            mutate.header("name", URLEncoder.encode(Objects.isNull(userMap.get("name")) ? "" : userMap.get("name").toString()), "UTF-8");
            mutate.header("username", URLEncoder.encode(Objects.isNull(userMap.get("username")) ? "" : userMap.get("username").toString()), "UTF-8");
            mutate.header("id", Objects.isNull(userMap.get("id")) ? "0" : userMap.get("id").toString());
            mutate.header("phone", Objects.isNull(userMap.get("phone")) ? "" : userMap.get("phone").toString());
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        } catch (Exception e) {
            log.error("token认证失败", e);
            return authError(resp, "认证出错，请重新登陆！");
        }
    }

    private Mono<Void> authError(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(new FailInfo(msg));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));

        return resp.writeWith(Flux.just(buffer));
    }


    /**
     * 忽略逻辑
     *
     * @param url       请求url
     * @param ignoreUrl 忽略url
     * @return Boolean
     */
    private Boolean ignore(String url, String ignoreUrl) {
        if (Objects.isNull(url)) {
            throw new LdException("请求 url 有误！");
        }
        return url.contains(ignoreUrl);
    }


}
