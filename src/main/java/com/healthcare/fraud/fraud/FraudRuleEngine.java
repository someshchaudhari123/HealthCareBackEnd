package com.healthcare.fraud.fraud;

import com.healthcare.fraud.entity.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FraudRuleEngine {

    private final List<FraudRule> rules;

    public int calculateFraudScore(Claim claim) {
        return rules.stream()
                .mapToInt(rule -> rule.evaluate(claim))
                .sum();
    }
}
