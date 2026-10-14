package com.example.rewards.dto;

/** Carries one customer's reward points for a calendar month. */
public record MonthlyRewards(String month, long points) { }
