package com.clouderwork.pojo;

import java.io.Serializable;

public class TLabel implements Serializable {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签
     */
    private String name;

    /**
     * t_label
     */
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     * @return id 标签id
     */
    public Long getId() {
        return id;
    }

    /**
     * 标签id
     * @param id 标签id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标签
     * @return name 标签
     */
    public String getName() {
        return name;
    }

    /**
     * 标签
     * @param name 标签
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}