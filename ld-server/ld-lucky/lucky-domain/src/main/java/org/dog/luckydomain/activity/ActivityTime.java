package org.dog.luckydomain.activity;

import lombok.Getter;
import org.dog.config.exception.LdCodeException;
import org.dog.config.exception.LdException;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/7 20:36
 * @Description:
 */
@Getter
public class ActivityTime {

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    public ActivityTime(LocalDateTime startTime, LocalDateTime endTime) {

        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            throw new LdException("活动时间不为空");
        }

        if (startTime.isAfter(endTime)) {
            //throw new ldException("活动时间非法！");
            throw new LdCodeException(5050, "活动时间非法！");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }


    public ActivityStatusEnum getStatus() {
        return getStatus(LocalDateTime.now());
    }

    public ActivityStatusEnum getStatus(LocalDateTime now) {

        if (now.isBefore(this.startTime)) {
            return ActivityStatusEnum.NOT_START;
        }

        if (now.isAfter(this.startTime) && now.isBefore(this.endTime)) {
            return ActivityStatusEnum.START;
        }

        return ActivityStatusEnum.END;
    }

}
