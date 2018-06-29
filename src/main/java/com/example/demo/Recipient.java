package com.example.demo;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Recipient {

    public Recipient() {
        messageReceived = new HashSet<>();
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Dish> getMessageReceived() {
        return messageReceived;
    }

    public String getRecipName() {
        return recipName;
    }

    public void setRecipName(String recipName) {
        this.recipName = recipName;
    }

    public void setMessageReceived(Set<Dish> messageReceived) {
        this.messageReceived = messageReceived;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String recipName;

    @ManyToMany(mappedBy = "recipie")
    private Set<Dish> messageReceived;
}