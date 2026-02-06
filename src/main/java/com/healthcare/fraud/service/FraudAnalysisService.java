package com.healthcare.fraud.service;

import com.healthcare.fraud.entity.Claim;
import com.healthcare.fraud.fraud.FraudRuleEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudAnalysisService {

    private final FraudRuleEngine fraudRuleEngine;

    public int analyzeClaim(Claim claim) {
        int score = fraudRuleEngine.calculateFraudScore(claim);

        claim.setFraudScore(score);

        if (score >= 60) {
            claim.setStatus("FRAUD_REVIEW");
        } else {
            claim.setStatus("APPROVED");
        }

        return score;
    }
}
