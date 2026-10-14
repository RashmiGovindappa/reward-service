package com.example.rewards.dto;

import java.util.List;

/** Carries one customer's monthly rewards and period total in API responses. */
public class CustomerRewards {
    private final Long customerId;
    private final String customerName;
    private final List<MonthlyRewards> monthlyRewards;
    private final long totalPoints;

    /** Creates a response DTO with the customer's monthly and total points. */
    public CustomerRewards(Long customerId, String customerName,
            List<MonthlyRewards> monthlyRewards, long totalPoints) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyRewards = List.copyOf(monthlyRewards);
        this.totalPoints = totalPoints;
    }

    /** Returns the customer identifier. */
    public Long getCustomerId() { return customerId; }

    /** Returns the customer display name. */
    public String getCustomerName() { return customerName; }

    /** Returns this customer's reward points grouped by month. */
    public List<MonthlyRewards> getMonthlyRewards() { return monthlyRewards; }

    /** Returns the customer's total reward points for the requested period. */
    public long getTotalPoints() { return totalPoints; }
}
