package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String  dishContainer;
    private String image;
    private int likeCount=0;
    private int dislikeCount=0;
    private String url;

    public long getId() {

        return id;
    }
    public void setId(long id) {

        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLast5minutes() {
        return last5minutes;
    }

    public void setLast5minutes(String last5minutes) {
        this.last5minutes = last5minutes;
    }

    public Set<Tasty> getTastyVotes() {
        return tastyVotes;
    }

    public void setTastyVotes(Set<Tasty> tastyVotes) {
        this.tastyVotes = tastyVotes;
    }

    public Set<Nasty> getNastyVotes() {
        return nastyVotes;
    }

    public void setNastyVotes(Set<Nasty> nastyVotes) {
        this.nastyVotes = nastyVotes;
    }

   // @NotEmpty
    private String imageurl;
    private String description;

    @Transient
    private String last5minutes;

    @ManyToMany()
    private Set<Recipient> recipie;

    @OneToMany(mappedBy = "theDish")
    private Set<Tasty> tastyVotes;

    @OneToMany(mappedBy = "theDish")
    private Set<Nasty> nastyVotes;















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

    public Dish() {
        recipie = new HashSet<>();
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

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