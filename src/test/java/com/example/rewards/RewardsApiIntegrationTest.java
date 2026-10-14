package com.example.rewards;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/** Integration tests for the REST endpoint, database seed data, and validation. */
@SpringBootTest
@AutoConfigureMockMvc
class RewardsApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    /** Verifies multiple customers, transactions, month breakdowns, and total points. */
    @Test
    void returnsSeededCustomerMonthlyAndTotalPoints() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "2026-01-01").param("endDate", "2026-03-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['1'].customerName").value("Alex Morgan"))
                .andExpect(jsonPath("$['1'].monthlyRewards", hasSize(3)))
                .andExpect(jsonPath("$['1'].monthlyRewards[0].points").value(65))
                .andExpect(jsonPath("$['1'].monthlyRewards[1].points").value(200))
                .andExpect(jsonPath("$['1'].monthlyRewards[2].points").value(40))
                .andExpect(jsonPath("$['1'].totalPoints").value(305))
                .andExpect(jsonPath("$['2'].totalPoints").value(53))
                .andExpect(jsonPath("$['3'].totalPoints").value(800));
    }

    /** Rejects a reversed reporting interval. */
    @Test
    void rejectsReversedDateRange() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "2026-03-01").param("endDate", "2026-01-01"))
                .andExpect(status().isBadRequest());
    }

    /** Rejects malformed ISO dates. */
    @Test
    void rejectsMalformedDate() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "not-a-date").param("endDate", "2026-03-31"))
                .andExpect(status().isBadRequest());
    }

    /** Returns an empty object for a valid interval with no transactions. */
    @Test
    void returnsEmptyResultForNoPurchases() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "2025-01-01").param("endDate", "2025-03-31"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());
    }
}
