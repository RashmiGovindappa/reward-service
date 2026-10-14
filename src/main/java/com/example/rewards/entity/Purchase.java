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

    protected Purchase() { }

    /** Creates an entity for the supplied purchase record. */
    public Purchase(Long customerId, String customerName, BigDecimal amount, LocalDate purchaseDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
    }

    /** Returns the customer identifier. */
    public Long getCustomerId() { return customerId; }
    /** Returns the customer display name. */
    public String getCustomerName() { return customerName; }
    /** Returns the purchase amount. */
    public BigDecimal getAmount() { return amount; }
    /** Returns the purchase date. */
    public LocalDate getPurchaseDate() { return purchaseDate; }
}
