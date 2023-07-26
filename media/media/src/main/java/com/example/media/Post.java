package com.example.media;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private LocalDateTime time;
    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public int getLikes() {
        return likes;
    }

    public void setLikes(int i) {
        this.likes = likes;
    }

    // Constructors, getters, and setters
}

