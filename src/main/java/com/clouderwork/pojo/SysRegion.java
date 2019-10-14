package com.clouderwork.pojo;

import java.io.Serializable;

public class SysRegion implements Serializable {
    /**
     * 
     */
    private Integer code;

    /**
     * 
     */
    private Integer parentcode;

    /**
     * 
     */
    private Byte type;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String fullname;

    /**
     * sys_region
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return code 
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 
     * @param code 
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 
     * @return parentCode 
     */
    public Integer getParentcode() {
        return parentcode;
    }

    /**
     * 
     * @param parentcode 
     */
    public void setParentcode(Integer parentcode) {
        this.parentcode = parentcode;
    }

    /**
     * 
     * @return type 
     */
    public Byte getType() {
        return type;
    }

    /**
     * 
     * @param type 
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return fullName 
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 
     * @param fullname 
     */
    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", parentcode=").append(parentcode);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", fullname=").append(fullname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}