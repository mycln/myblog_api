package com.clouderwork.pojo;

import java.io.Serializable;

public class TArticle implements Serializable {
    /**
     * 文章id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 浏览次数
     */
    private Integer viewTimes;

    /**
     * 评论数
     */
    private Integer commentsTimes;

    /**
     * 权重
     */
    private Integer level;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 修改时间
     */
    private Integer utime;

    /**
     * 内容
     */
    private String content;

    /**
     * t_article
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 浏览次数
     * @return view_times 浏览次数
     */
    public Integer getViewTimes() {
        return viewTimes;
    }

    /**
     * 浏览次数
     * @param viewTimes 浏览次数
     */
    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    /**
     * 评论数
     * @return comments_times 评论数
     */
    public Integer getCommentsTimes() {
        return commentsTimes;
    }

    /**
     * 评论数
     * @param commentsTimes 评论数
     */
    public void setCommentsTimes(Integer commentsTimes) {
        this.commentsTimes = commentsTimes;
    }

    /**
     * 权重
     * @return level 权重
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 权重
     * @param level 权重
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 作者
     * @return author 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 作者
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 创建时间
     * @return ctime 创建时间
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 创建时间
     * @param ctime 创建时间
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     * 修改时间
     * @return utime 修改时间
     */
    public Integer getUtime() {
        return utime;
    }

    /**
     * 修改时间
     * @param utime 修改时间
     */
    public void setUtime(Integer utime) {
        this.utime = utime;
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", viewTimes=").append(viewTimes);
        sb.append(", commentsTimes=").append(commentsTimes);
        sb.append(", level=").append(level);
        sb.append(", author=").append(author);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}