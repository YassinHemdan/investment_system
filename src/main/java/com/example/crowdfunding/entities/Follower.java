package com.example.crowdfunding.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class Follower extends BaseEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "generator"
    )
    @SequenceGenerator(
            name = "generator",
            sequenceName = "follower_seq",
            allocationSize = 1
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id")
    private UserEntity follower;

}
