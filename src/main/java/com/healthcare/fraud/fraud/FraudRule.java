package com.healthcare.fraud.fraud;

import com.healthcare.fraud.entity.Claim;

public interface FraudRule {
    int evaluate(Claim claim);
}
