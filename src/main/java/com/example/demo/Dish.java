package com.example.demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dish {
    public long getId() {
        return id;
    }

    public Dish() {
        recipie = new HashSet<>();
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getDishContainer() {
        return dishContainer;
    }

    public void setDishContainer(String dishContainer) {
        this.dishContainer = dishContainer;
    }

    public Set<Recipient> getRecipie() {
        return recipie;
    }

    public void setRecipie(Set<Recipient> recipie) {
        this.recipie = recipie;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  dishContainer;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    @ManyToMany()
    private Set<Recipient> recipie;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    private int likeCount=0;

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    private int dislikeCount=0;
    private String url;


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }
        @Override
        public String toString() {
            return "Dish{" +
                    "id=" + id +
                    ", dishContainer='" + dishContainer + '\'' +
                    ", image='" + image + '\'' +
                    ", recipie=" + recipie +
                    ", likeCount=" + likeCount +
                    ", dislikeCount=" + dislikeCount +
                    ", url='" + url + '\'' +
                    '}';

    }
}