package com.example.rewards.dto;

import lombok.Getter;

/** Carries one customer's reward points for a calendar month. */
@Getter
public class MonthlyRewards {
    private final String month;
    private final long points;

    /** Creates a monthly reward response value. */
    public MonthlyRewards(String month, long points) {
        this.month = month;
        this.points = points;
    }

}
