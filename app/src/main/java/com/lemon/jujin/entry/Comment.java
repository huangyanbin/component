package com.lemon.jujin.entry;


import java.io.Serializable;

/**
 * Created by David on 2017/5/23.
 */
public class Comment implements Serializable{

    private static final long serialVersionUID = -669130611940928240L;
    private int id;
    private User user;
    private Article article;
    private String content;
    private long commitTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(long commitTime) {
        this.commitTime = commitTime;
    }
}
