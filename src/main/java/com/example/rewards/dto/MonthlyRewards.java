package com.example.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Carries one customer's reward points for a calendar month. */
@Getter
@AllArgsConstructor
public class MonthlyRewards {
    private final String month;
    private final long points;

}
