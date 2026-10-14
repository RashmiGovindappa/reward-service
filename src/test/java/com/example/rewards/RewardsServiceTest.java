package com.example.rewards;

import com.example.rewards.service.RewardsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class RewardsServiceTest {
    private final RewardsService service = new RewardsService();

    @Test
    void calculatesTierBoundaries() {
        assertEquals(0, service.rewardPoints(new BigDecimal("0.00")));
        assertEquals(0, service.rewardPoints(new BigDecimal("49.99")));
        assertEquals(1, service.rewardPoints(new BigDecimal("51.99")));
        assertEquals(50, service.rewardPoints(new BigDecimal("100.00")));
        assertEquals(52, service.rewardPoints(new BigDecimal("101.99")));
        assertEquals(90, service.rewardPoints(new BigDecimal("120.00")));
    }

    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> service.rewardPoints(new BigDecimal("-1.00")));
    }
}
