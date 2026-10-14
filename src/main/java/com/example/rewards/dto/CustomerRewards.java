package com.example.rewards.dto;

import java.util.List;

/** Contains one customer's monthly reward breakdown and period total. */
public record CustomerRewards(Long customerId, String customerName,
        List<MonthlyRewards> monthlyRewards, long totalPoints) { }
