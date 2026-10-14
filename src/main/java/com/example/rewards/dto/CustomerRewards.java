package com.example.rewards.dto;

import java.util.List;
import lombok.Getter;

/** Carries one customer's monthly rewards and period total in API responses. */
@Getter
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

}
