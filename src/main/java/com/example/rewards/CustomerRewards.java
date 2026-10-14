package com.example.rewards;

import java.util.List;

/** Represents a customer's monthly reward breakdown and total. */
public record CustomerRewards(Long customerId, String customerName, List<MonthlyRewards> monthlyRewards, long totalPoints) { }
