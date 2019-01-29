package cn.hang.neuq.entity.vo;

import cn.hang.neuq.entity.po.Gpa;
import lombok.Data;

import java.util.List;

/**
 * @author lihang15
 * @description
 * @create 2019-01-28 19:41
 **/
@Data
public class SemesterGpaVO {
    private List<Gpa> gpaList;
    private UserJwInfoVO jwUserInfo;
}
