package com.example.crowdfunding.entities;

import com.example.crowdfunding.dtos.OtherProfileDTO;
import com.example.crowdfunding.dtos.UserPortfolioDTO;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NamedNativeQuery(
        name = "UserEntity.portfolio",
            query = """
                    WITH query1 AS (
                                    SELECT COUNT(*) AS "total tokens" , qd.department_id, qd.size - COUNT(*) AS availableArea
                                    FROM tokens qt JOIN departments qd ON qd.department_id = qt.department_id GROUP BY qd.department_id
                                    )
                    				, query2 as(
                    							select d.department_id ,cast(count(t.department_id) as integer) as ownedTokens
                    							from tokens t
                    							join departments d on t.department_id = d.department_id
                    							where user_id = :user_id
                    							group by d.department_id
                    				)
                                    SELECT distinct on(p.department_id) d.department_id AS id, d.country, d.city,
                                    d.street, d.size  AS area,  max(p.price) AS price
                                    , MAX(p.price) / d.size  AS tokenPrice, availableArea
                    				, ownedTokens
                                    FROM departments d\s
                    				JOIN investments i ON i.department_id = d.department_id
                                    JOIN users u ON u.user_id = i.user_id
                                    JOIN price_history p ON p.department_id = d.department_id
                                    JOIN query1 ON query1.department_id = d.department_id
                    				JOIN query2 ON query2.department_id = d.department_id
                    			\t
                                    WHERE u.user_id = :user_id
                                    GROUP BY p.department_id, p.date, d.department_id, d.country, d.city, d.street, area , availableArea,
                    				ownedTokens
                   \s"""
        ,
        resultSetMapping = "Mapping.UserPortfolioDTO"
)
@SqlResultSetMapping(
        name = "Mapping.UserPortfolioDTO",
        classes = @ConstructorResult(
                targetClass = UserPortfolioDTO.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "country"),
                        @ColumnResult(name = "city"),
                        @ColumnResult(name = "street"),
                        @ColumnResult(name = "area"),
                        @ColumnResult(name = "availableArea"),
                        @ColumnResult(name = "price"),
                        @ColumnResult(name = "tokenPrice"),
                        @ColumnResult(name = "ownedTokens")
                }
        )
)

@NamedNativeQuery(
        name = "UserEntity.otherProfile",
        query = """
				SELECT u.username as userName,
				CAST(COUNT(f.follower_id) AS INTEGER) AS numberOfFollowers
				FROM users u
				LEFT JOIN followers f ON u.user_id = f.user_id
				WHERE u.user_id = :user_id
				GROUP BY u.user_id, u.username\s
				"""
        ,
        resultSetMapping = "Mapping.OtherProfileDTO"
)
@SqlResultSetMapping(
        name = "Mapping.OtherProfileDTO",
        classes = @ConstructorResult(
                targetClass = OtherProfileDTO.class,
                columns = {
                        @ColumnResult(name = "userName"),
                        @ColumnResult(name = "numberOfFollowers"),
                }
        )
)

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "generator"
    )
    @SequenceGenerator(
            name = "generator",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    @Column(name = "user_id")
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false)
    private String country;
    private String city;
    private String street;

    @OneToMany(mappedBy = "user")
    private List<Investment> investments;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @OneToMany
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id")
    private List<Follower> followers;

    @OneToMany
    @JoinColumn(name = "followee_id", referencedColumnName = "user_id")
    private List<Follower> followings;

    @ManyToMany(fetch = FetchType.EAGER) // revise fetch type
    @JoinTable(
            name = "users_auths",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;
}
