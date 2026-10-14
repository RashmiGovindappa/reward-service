package com.example.rewards.service;

import com.example.rewards.dto.CustomerRewards;
import com.example.rewards.dto.MonthlyRewards;
import com.example.rewards.entity.Purchase;
import com.example.rewards.repository.PurchaseRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/** Calculates per-transaction points and aggregates them by customer and month. */
@Service
public class RewardsService {
    private static final BigDecimal FIFTY = BigDecimal.valueOf(50);
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMMM uuuu");
    private final PurchaseRepository repository;

    /** Creates a rewards service backed by the purchase repository. */
    public RewardsService(PurchaseRepository repository) { this.repository = repository; }

    /** Calculates monthly and total points for all customers in an inclusive period. */
    public Map<Long, CustomerRewards> calculate(LocalDate start, LocalDate end) {
        Map<Long, Map<YearMonth, Long>> byCustomer = new LinkedHashMap<>();
        Map<Long, String> names = new LinkedHashMap<>();
        for (Purchase purchase : repository.findInPeriod(start, end)) {
            Long id = purchase.getCustomerId();
            names.putIfAbsent(id, purchase.getCustomerName());
            YearMonth month = YearMonth.from(purchase.getPurchaseDate());
            byCustomer.computeIfAbsent(id, ignored -> new LinkedHashMap<>())
                    .merge(month, rewardPoints(purchase.getAmount()), Long::sum);
        }
        Map<Long, CustomerRewards> result = new LinkedHashMap<>();
        byCustomer.forEach((id, months) -> {
            long total = months.values().stream().mapToLong(Long::longValue).sum();
            var monthly = months.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .map(entry -> new MonthlyRewards(MONTH_FORMAT.format(entry.getKey()), entry.getValue())).toList();
            result.put(id, new CustomerRewards(id, names.get(id), monthly, total));
        });
        return result;
    }

    /** Applies 1 point per whole dollar in $50-$100 and 2 per whole dollar above $100. */
    public long rewardPoints(BigDecimal amount) {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("Purchase amount must be zero or greater");
        }
        long firstTier = amount.compareTo(FIFTY) > 0 ? amount.min(HUNDRED).subtract(FIFTY).longValue() : 0;
        long secondTier = amount.compareTo(HUNDRED) > 0 ? amount.subtract(HUNDRED).longValue() * 2 : 0;
        return firstTier + secondTier;
    }
}
