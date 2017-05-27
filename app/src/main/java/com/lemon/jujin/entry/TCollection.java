package com.lemon.jujin.entry;


import java.io.Serializable;
import java.util.Date;


public class TCollection implements Serializable{

    private static final long serialVersionUID = 2991955077675117002L;
    private int id ;
    private Article article;
    private CollectionType type;
    private long collectionTime ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public CollectionType getType() {
        return type;
    }

    public void setType(CollectionType type) {
        this.type = type;
    }

    public long getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(long collectionTime) {
        this.collectionTime = collectionTime;
    }
}
