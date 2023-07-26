package com.example.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/posts")
    public Post addPost(@PathVariable Long userId, @RequestBody Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        post.setUser(user);
        user.getPosts().add(post);
        userRepository.save(user);
        return post;
    }

    @GetMapping("/most-liked-user")
    public User findMostLikedUser() {
        return userRepository.findAll().stream()
                .max(Comparator.comparingInt(user -> user.getPosts().stream().mapToInt(Post::getLikes).sum()))
                .orElse(null);
    }
}
