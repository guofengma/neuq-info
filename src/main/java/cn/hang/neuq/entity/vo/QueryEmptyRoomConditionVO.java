package cn.hang.neuq.entity.vo;

import cn.hang.neuq.entity.po.Dict;
import lombok.Data;

import java.util.List;

/**
 * 查询空闲教室所需前置条件
 *
 * @author lihang15
 * @description
 * @create 2019-01-28 20:00
 **/
@Data
public class QueryEmptyRoomConditionVO {

    private List<DictVO> buildingList;
    private String termWeekNumber;
    private String currentWeekNumber;


}
