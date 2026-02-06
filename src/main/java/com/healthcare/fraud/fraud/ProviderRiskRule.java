package com.healthcare.fraud.fraud;

import com.healthcare.fraud.entity.Claim;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProviderRiskRule implements FraudRule {

    private static final Set<String> HIGH_RISK_PROVIDERS =
            Set.of("HSP1001", "HSP9999");

    @Override
    public int evaluate(Claim claim) {

        if (claim.getProvider() == null) {
            return 0;
        }

        return HIGH_RISK_PROVIDERS.contains(
                claim.getProvider().getProviderCode()
        ) ? 25 : 0;
    }
}
