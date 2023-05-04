package org.dog.config.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.TokenAuthException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Odin
 * @Date: 2023/5/4 14:16
 * @Description:
 */
@Slf4j
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "odin_code";
    /**
     * 过期时间
     */
    private static final long EXPIRATION = 2 * 24 * 60 * 60L;

    /**
     * 生成用户token 设置token超时时间
     * 添加用户信息,放入map中
     */
    public static String createToken(Map<String, Object> params) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        JWTCreator.Builder builder = JWT.create().withHeader(header);
        params.forEach((k, v) -> builder.withClaim(k, v.toString()));
        return builder.withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Object> verifyToken(String token) {
        DecodedJWT jwt = null;
        Map<String, Object> result = new HashMap<>();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);

            jwt.getClaims().forEach((k, v) -> result.put(k, v.asString()));
        } catch (Exception e) {
            //错误处理
            System.out.println("verifyToken_error" + e);
            throw new TokenAuthException(e.getMessage());
        }
        return result;
    }


    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "odin");
        params.put("url", "code-farmer.cloud");
        String token = createToken(params);
        log.info("token====>{}", token);
        Map<String, Object> stringObjectMap = verifyToken(token);
        log.info("stringObjectMap====>{}", stringObjectMap);
    }

}
