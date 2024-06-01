package com.example.crowdfunding.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "generator"
    )
    @SequenceGenerator(
            name = "generator",
            sequenceName = "payment_seq",
            allocationSize = 1
    )
    @Column(name = "payment_id")
    private int id;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
    @Column(name = "payment_time", nullable = false)
    private LocalTime paymentDime;
    @Column(name = "payment_type", nullable = false)
    private String paymentType;
    @Column(name = "total_paid", nullable = false)
    private double totalPaid;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "investment_id", referencedColumnName = "investment_id")
    private Investment investment;
}
