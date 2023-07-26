package com.example.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM users u INNER JOIN posts p ON u.user_id = p.user_id " +
            "GROUP BY u.user_id " +
            "ORDER BY SUM(p.likes) DESC " +
            "LIMIT 1", nativeQuery = true)
    User findUserWithMostLikes();
}

