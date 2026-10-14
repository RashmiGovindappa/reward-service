package com.example.rewards.controller;

import com.example.rewards.dto.CustomerRewards;
import com.example.rewards.service.RewardsService;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/** Handles HTTP requests for customer reward summaries. */
@RestController
public class RewardsController {

    @Autowired
    private RewardsService service;

    /** Returns monthly and total points for the inclusive date range. */
    @GetMapping("/api/rewards")
    public Map<Long, CustomerRewards> rewards(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "startDate must be on or before endDate");
        }
        return service.calculate(startDate, endDate);
    }
}
