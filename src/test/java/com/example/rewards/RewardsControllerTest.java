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

/** Integration tests for reward controller responses and request validation. */
@SpringBootTest
@AutoConfigureMockMvc
class RewardsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /** Checks monthly and total points for several seeded customers. */
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

    /** Rejects a date range whose start is after its end. */
    @Test
    void rejectsReversedDateRange() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "2026-03-01").param("endDate", "2026-01-01"))
                .andExpect(status().isBadRequest());
    }

    /** Rejects a malformed ISO date parameter. */
    @Test
    void rejectsMalformedDate() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "not-a-date").param("endDate", "2026-03-31"))
                .andExpect(status().isBadRequest());
    }

    /** Returns no customer summaries when the date interval has no purchases. */
    @Test
    void returnsEmptyResultForNoPurchases() throws Exception {
        mockMvc.perform(get("/api/rewards").param("startDate", "2025-01-01").param("endDate", "2025-03-31"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());
    }
}
