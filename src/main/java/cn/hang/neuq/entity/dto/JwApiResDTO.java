package cn.hang.neuq.entity.dto;

import lombok.Data;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 22:31
 **/
@Data
public class JwApiResDTO<T> {
    private String message;
    private Integer code;
    private T data;
}
