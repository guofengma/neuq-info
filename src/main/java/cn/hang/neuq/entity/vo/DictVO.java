package cn.hang.neuq.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lihang15
 * @description
 * @create 2019-01-28 20:10
 **/
@Data
public class DictVO {
    private String name;

    private String value;


    private List<DictVO> childDicts;

}
