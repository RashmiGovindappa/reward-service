package com.example.rewards.dto;

import java.util.List;

/** Carries one customer's monthly rewards and period total in API responses. */
public record CustomerRewards(Long customerId, String customerName,
        List<MonthlyRewards> monthlyRewards, long totalPoints) { }
