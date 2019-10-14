package com.clouderwork.pojo;

import java.io.Serializable;

public class TCategory implements Serializable {
    /**
     * 类别id
     */
    private Long id;

    /**
     * 类别
     */
    private String name;

    /**
     * 
     */
    private Byte pid;

    /**
     * t_category
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类别id
     * @return id 类别id
     */
    public Long getId() {
        return id;
    }

    /**
     * 类别id
     * @param id 类别id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 类别
     * @return name 类别
     */
    public String getName() {
        return name;
    }

    /**
     * 类别
     * @param name 类别
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return pid 
     */
    public Byte getPid() {
        return pid;
    }

    /**
     * 
     * @param pid 
     */
    public void setPid(Byte pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}