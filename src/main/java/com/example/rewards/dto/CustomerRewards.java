package com.example.rewards.dto;

import java.util.List;

public record CustomerRewards(Long customerId, String customerName,
        List<MonthlyRewards> monthlyRewards, long totalPoints) { }
