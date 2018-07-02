package com.example.demo;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tasty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime voteAt;
    private String tastyOrNasty;

    @ManyToOne()
    private Dish theDish;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getVoteAt() {
        return voteAt;
    }

    public void setVoteAt() {
        this.voteAt = LocalDateTime.now();
    }

    public String getTastyOrNasty() {
        return tastyOrNasty;
    }

    public void setTastyOrNasty(String tastyOrNasty) {
        this.tastyOrNasty = tastyOrNasty;
    }

    public Dish getTheDish() {
        return theDish;
    }

    public void setTheDish(Dish theDish) {
        this.theDish = theDish;
    }

}
