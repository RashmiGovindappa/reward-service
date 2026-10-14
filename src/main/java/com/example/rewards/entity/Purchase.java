package com.example.rewards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;

/** A recorded purchase used to calculate customer reward points. */
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private Long customerId;
    @Getter
    private String customerName;
    @Getter
    private BigDecimal amount;
    @Getter
    private LocalDate purchaseDate;

    /** Provides the no-argument constructor required by JPA entity loading. */
    protected Purchase() { }

}
