package com.example.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "UPDATE posts SET likes = likes + 1 WHERE post_id = :postId", nativeQuery = true)
    void incrementLikes(Long postId);
}
