package com.example.crowdfunding.entities;

import com.example.crowdfunding.dtos.ViewDepartmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NamedNativeQuery(
        name = "Department.viewDepartment",
        query = """
                with query1 as (
                	SELECT COUNT(*) AS "total tokens" , qd.department_id, qd.size - COUNT(*) AS "availableArea"\s
                	FROM tokens qt\s
                	JOIN departments qd ON qd.department_id = qt.department_id
                	where qd.department_id = 1
                	GROUP BY qd.department_id
                )
                SELECT d.department_id AS "id", d.country, d.city, d.street, d.size  AS "area",  MAX(p.price) AS "price"
                , MAX(p.price) / d.size  AS "tokenPrice", "availableArea"\s
                FROM departments d
                JOIN price_history p ON p.department_id = d.department_id\s
                JOIN query1 ON query1.department_id = d.department_id
                WHERE d.department_id = 1
                GROUP BY d.department_id, d.country, d.city, d.street, "area" ,  "availableArea"
                """,
        resultSetMapping = "Mapping.ViewDepartmentDTO"
)
@SqlResultSetMapping(
        name = "Mapping.ViewDepartmentDTO",
        classes = {
                @ConstructorResult(
                        targetClass = ViewDepartmentDTO.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "country"),
                                @ColumnResult(name = "city"),
                                @ColumnResult(name = "street"),
                                @ColumnResult(name = "area"),
                                @ColumnResult(name = "availableArea"),
                                @ColumnResult(name = "price"),
                                @ColumnResult(name = "tokenPrice")
                        }
                )
        }
)



@Data
@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "generator"
    )
    @SequenceGenerator(
            name = "generator",
            sequenceName = "department_seq",
            allocationSize = 1
    )
    @Column(name = "department_id")
    private int id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(name = "size", nullable = false)
    private int area;

    @OneToMany(mappedBy = "department")
    private List<Investment> investments;

    @OneToMany(mappedBy = "department")
    private List<Token> tokens;

    @OneToMany(mappedBy = "department")
    private List<PriceHistory> priceHistories;
}
