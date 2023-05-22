package org.dog.luckydomain.record;

import org.dog.config.enums.RecordStatusEnum;
import org.dog.config.util.AssertUtil;

/**
 * @Author: Odin
 * @Date: 2023/5/21 00:04
 * @Description:
 */
public class RecordStatus {

    private RecordStatusEnum state;

    public Integer getState() {
        return this.state.getValue();
    }

    public RecordStatus(Integer state) {
        AssertUtil.isTrue(state < 0, "记录状态无效！");

        if (RecordStatusEnum.STATUE_0.getValue() == state){
            this.state = RecordStatusEnum.STATUE_1;
            return;
        }

        if (RecordStatusEnum.STATUE_1.getValue() == state){
            this.state = RecordStatusEnum.STATUE_1;
            return;
        }

        if (RecordStatusEnum.STATUE_2.getValue() == state){
            this.state = RecordStatusEnum.STATUE_2;
            return;
        }

        if (RecordStatusEnum.STATUE_3.getValue() == state){
            this.state = RecordStatusEnum.STATUE_3;
            return;
        }

        if (RecordStatusEnum.STATUE_4.getValue() == state){
            this.state = RecordStatusEnum.STATUE_4;
            return;
        }

        AssertUtil.isTrue(true, "记录状态无效！");
    }


}
