package com.example.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @PutMapping("/{postId}")
    public Post likePost(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);

        Notification notification = new Notification();
        notification.setPost(post);
        notification.setTime(LocalDateTime.now());
        notificationRepository.save(notification);

        return post;
    }
}
