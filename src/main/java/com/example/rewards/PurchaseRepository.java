package com.example.rewards;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Provides database access to purchase records. */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    /** Finds purchases in the inclusive reporting date interval. */
    @Query("select p from Purchase p where p.purchaseDate >= :start and p.purchaseDate <= :end order by p.customerId, p.purchaseDate")
    List<Purchase> findInPeriod(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
