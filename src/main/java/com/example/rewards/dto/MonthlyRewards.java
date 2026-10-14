package com.example.rewards.dto;

/** Carries one customer's reward points for a calendar month. */
public class MonthlyRewards {
    private final String month;
    private final long points;

    /** Creates a monthly reward response value. */
    public MonthlyRewards(String month, long points) {
        this.month = month;
        this.points = points;
    }

    /** Returns the calendar month label. */
    public String getMonth() { return month; }

    /** Returns the reward points earned in this month. */
    public long getPoints() { return points; }
}
