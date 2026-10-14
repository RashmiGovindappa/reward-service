package com.example.rewards.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** Carries one customer's monthly rewards and period total in API responses. */
@Getter
@AllArgsConstructor
public class CustomerRewards {
    private final Long customerId;
    private final String customerName;
    private final List<MonthlyRewards> monthlyRewards;
    private final long totalPoints;

}
