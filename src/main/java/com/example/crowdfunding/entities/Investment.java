package com.example.crowdfunding.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "investments")
public class Investment extends BaseEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "generator"
    )
    @SequenceGenerator(
            name = "generator",
            sequenceName = "investment_seq",
            allocationSize = 1
    )
    @Column(name = "investment_id")
    private int id;
    @Column(name = "date", nullable = false)
    private LocalDate date_of_investment;
    @Column(name = "time", nullable = false)
    private LocalTime time_of_investment;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private  Department department;

    @OneToMany(mappedBy = "investment")
    private List<Token> tokens;

    @OneToOne(mappedBy = "investment")
    private Payment payment;
}
