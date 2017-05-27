package com.lemon.jujin.entry;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by David on 2017/5/23.
 */
public class Share {
    private int id;
    private int uid;
    private int articleID;
    private long shareTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public long getShareTime() {
        return shareTime;
    }

    public void setShareTime(long shareTime) {
        this.shareTime = shareTime;
    }
}
