package com.clouderwork.pojo.vo;

import java.io.Serializable;

public class CategoryVo implements Serializable {
    /**
     * 类别id
     */
    private Long id;

    /**
     * 类别
     */
    private String name;

    /**
     * 类别pid
     */
    private Byte pid;

    /**
     * 文章数量
     */
    private int articleNum;

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

    public int getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }
}