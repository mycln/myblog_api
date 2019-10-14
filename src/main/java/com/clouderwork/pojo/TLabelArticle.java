package com.clouderwork.pojo;

import java.io.Serializable;

public class TLabelArticle implements Serializable {
    /**
     * 标签映射id
     */
    private Long id;

    /**
     * 标签id
     */
    private Long labelId;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * t_label_article
     */
    private static final long serialVersionUID = 1L;

    /**
     * 标签映射id
     * @return id 标签映射id
     */
    public Long getId() {
        return id;
    }

    /**
     * 标签映射id
     * @param id 标签映射id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标签id
     * @return label_id 标签id
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 标签id
     * @param labelId 标签id
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
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
        sb.append(", labelId=").append(labelId);
        sb.append(", articleId=").append(articleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}