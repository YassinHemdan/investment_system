package com.example.crowdfunding.repos;

import com.example.crowdfunding.entities.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PriceHistoryRepo extends JpaRepository<PriceHistory, Integer>{
    @Query(
            nativeQuery = true,
            value = """
                    select distinct on (department_id) * from price_history
                    where department_id = :dep_id
                    order by department_id, date desc
                    """
    )
    PriceHistory findLatestPrice(@Param("dep_id") int dep_id);

    @Query(
            nativeQuery = true,
            value = """
                    select * from price_history where department_id = :id
                    """
    )
    List<PriceHistory> findApartmentHistory(int id);
}
