package com.example.rewards;

import com.example.rewards.service.RewardsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/** Unit tests for reward point tier calculations. */
class RewardsServiceTest {
    private final RewardsService service = new RewardsService();

    /** Verifies points at the tier boundaries and for fractional dollar amounts. */
    @Test
    void calculatesTierBoundaries() {
        assertEquals(0, service.rewardPoints(new BigDecimal("0.00")));
        assertEquals(0, service.rewardPoints(new BigDecimal("49.99")));
        assertEquals(1, service.rewardPoints(new BigDecimal("51.99")));
        assertEquals(50, service.rewardPoints(new BigDecimal("100.00")));
        assertEquals(52, service.rewardPoints(new BigDecimal("101.99")));
        assertEquals(90, service.rewardPoints(new BigDecimal("120.00")));
    }

    /** Verifies negative transaction amounts are rejected. */
    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> service.rewardPoints(new BigDecimal("-1.00")));
    }
}
