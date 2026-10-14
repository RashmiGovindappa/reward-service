package com.example.rewards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/** A recorded purchase used to calculate customer reward points. */
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String customerName;
    private BigDecimal amount;
    private LocalDate purchaseDate;

    /** Provides the no-argument constructor required by JPA entity loading. */
    protected Purchase() { }

    /** Returns the customer identifier. */
    public Long getCustomerId() { return customerId; }
    /** Returns the customer display name. */
    public String getCustomerName() { return customerName; }
    /** Returns the purchase amount. */
    public BigDecimal getAmount() { return amount; }
    /** Returns the purchase date. */
    public LocalDate getPurchaseDate() { return purchaseDate; }
}
