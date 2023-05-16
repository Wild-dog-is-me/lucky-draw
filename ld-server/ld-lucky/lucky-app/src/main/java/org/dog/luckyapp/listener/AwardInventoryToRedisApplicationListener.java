package org.dog.luckyapp.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyapp.listener.event.ActivityCreateEvent;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckydomain.award.AwardEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/15 20:39
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AwardInventoryToRedisApplicationListener implements ApplicationListener<ActivityCreateEvent> {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * lucky-draw:activity:award:活动id:奖项id
     */
    private static final String awardInventoryKey = "lucky-draw:activity:award:";


    @Override
    public void onApplicationEvent(ActivityCreateEvent event) {
        ActivityConfigVO activityConfig = event.getActivityConfig();
        for (AwardVO awardVO : activityConfig.getAwardVOList()) {
            AwardEntity awardEntity = AwardAssembler.toAwardEntity(awardVO);
            if (Boolean.FALSE.equals(awardEntity.isPrize())) {
                continue;
            }
            String key = getKey(activityConfig.getActivityVO().getId(), awardVO.getId());
            redisTemplate.opsForValue().set(key, awardVO.getNumber());

            log.info("ActivityCreateEvent ===> ActivityId:{}，awardId:{}，存入库存：{} Redis成功...",
                    activityConfig.getActivityVO().getId(),
                    awardVO.getId(),
                    redisTemplate.opsForValue().get(key)
            );
        }
    }

    public static String getKey(Long activityId, Long awardId) {
        return awardInventoryKey + activityId + ":" + awardId;
    }

}
