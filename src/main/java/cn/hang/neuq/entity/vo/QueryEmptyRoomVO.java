package cn.hang.neuq.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lihang15
 * @description
 * @create 2019-01-27 12:37
 **/
@Data
public class QueryEmptyRoomVO {

    private String campus;
    private List<Integer> week;
    private List<Integer> day;
    private List<Integer> section;
    private String buildingNumber;
    private String roomType;
    private String term;
}
