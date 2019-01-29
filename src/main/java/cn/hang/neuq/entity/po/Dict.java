package cn.hang.neuq.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * dict
 * @author 
 */
@Data
public class Dict implements Serializable {
    private Long id;

    private String type;

    private String name;

    private String value;

    private String remark;

    private Long pId;

    private List<Dict> childDicts;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }
}