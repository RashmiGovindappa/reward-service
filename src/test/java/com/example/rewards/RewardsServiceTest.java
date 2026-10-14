package com.example.rewards;

import com.example.rewards.repository.PurchaseRepository;
import com.example.rewards.service.RewardsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for transaction tier calculations. */
class RewardsServiceTest {
    private RewardsService service;

    /** Creates a service with an unused mocked repository. */
    @BeforeEach
    void setUp() { service = new RewardsService(mock(PurchaseRepository.class)); }

    /** Covers boundaries and fractional amounts across both tiers. */
    @Test
    void calculatesTierBoundaries() {
        assertEquals(0, service.rewardPoints(new BigDecimal("0.00")));
        assertEquals(0, service.rewardPoints(new BigDecimal("49.99")));
        assertEquals(1, service.rewardPoints(new BigDecimal("51.99")));
        assertEquals(50, service.rewardPoints(new BigDecimal("100.00")));
        assertEquals(52, service.rewardPoints(new BigDecimal("101.99")));
        assertEquals(90, service.rewardPoints(new BigDecimal("120.00")));
    }

    /** Rejects invalid negative purchase amounts instead of awarding points. */
    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> service.rewardPoints(new BigDecimal("-1.00")));
    }
}
