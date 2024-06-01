package com.example.crowdfunding.repos;

import com.example.crowdfunding.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowerRepo extends JpaRepository<Follower, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM followers WHERE user_id = :user_id AND follower_id = :follower_id")
    Follower findFollower(@Param("user_id") int user_id, @Param("follower_id") int follower_id);
}