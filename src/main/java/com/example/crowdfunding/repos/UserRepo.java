package com.example.crowdfunding.repos;

import com.example.crowdfunding.dtos.*;
import com.example.crowdfunding.entities.Authority;
import com.example.crowdfunding.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    @Query(nativeQuery = true)
    List<UserPortfolioDTO> portfolio(@Param("user_id") int user_id);

    @Query(nativeQuery = true)
    OtherProfileDTO otherProfile(@Param("user_id") int user_id);


    @Query("SELECT new com.example.crowdfunding.dtos.UserFollowersDTO(f.id, f.userName) " +
            "FROM UserEntity u JOIN Follower f1 ON u.id = f1.user.id " +
            "JOIN UserEntity f ON f.id = f1.follower.id " +
            "WHERE u.id = :user_id "
    )
    List<UserFollowersDTO> getUserFollowers(@Param("user_id") int user_id);


    @Query("SELECT new com.example.crowdfunding.dtos.UserFollowingsDTO(f.id, f.userName) " +
            "FROM UserEntity u JOIN Follower f1 ON u.id = f1.follower.id " +
            "JOIN UserEntity f ON f.id = f1.user.id " +
            "WHERE u.id = :user_id "
    )
    List<UserFollowingsDTO> getUserFollowings(@Param("user_id") int user_id);

    @Query(nativeQuery = true , name = "SELECT user_id FROM users WHERE username = :username")
    Optional<UserEntity> findByUserName(String username);

    @Query
    Boolean existsByUserName(String username);

    @Query(
            nativeQuery = true,
            value = """
                    with q as(
                    	select i.user_id as id from investments i
                    )select distinct * from users u , q where u.user_id = q.id
                    """
    )
    List<UserEntity> findInvestors();

    @Query(nativeQuery = true , value = "SELECT * FROM users WHERE email = :email")
    UserEntity findByEmail(String email);

    @Query(nativeQuery = true , value = "SELECT * FROM users WHERE phone = :phone")
    UserEntity findByPhone(String phone);

    @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.username = :username")
    UserEntity getUserWithAuthorities(String username);
}





