package cn.hang.neuq.entity.vo;

import lombok.Data;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 12:08
 **/
@Data
public class DecodeUserInfoVO {

    private String encryptedData;
    private String iv;
    private String openId;

}
