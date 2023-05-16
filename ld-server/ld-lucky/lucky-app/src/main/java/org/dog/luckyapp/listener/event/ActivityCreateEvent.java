package org.dog.luckyapp.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: Odin
 * @Date: 2023/5/15 20:45
 * @Description:
 */
@Slf4j
public class ActivityCreateEvent extends ApplicationEvent {
    /**
     * 活动创建
     */
    private final ActivityConfigVO activityConfigVO;

    public ActivityConfigVO getActivityConfig() {
        return activityConfigVO;
    }


    public ActivityCreateEvent(Object source, ActivityConfigVO activityConfigVO) {
        super(source);
        this.activityConfigVO = activityConfigVO;
    }
}
