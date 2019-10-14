package com.clouderwork.pojo;

import java.io.Serializable;

public class TCategoryArticle implements Serializable {
    /**
     * 分类映射id
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * t_category_article
     */
    private static final long serialVersionUID = 1L;

    /**
     * 分类映射id
     * @return id 分类映射id
     */
    public Long getId() {
        return id;
    }

    /**
     * 分类映射id
     * @param id 分类映射id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 分类id
     * @return category_id 分类id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 分类id
     * @param categoryId 分类id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 文章id
     * @return article_id 文章id
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     * @param articleId 文章id
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", articleId=").append(articleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}