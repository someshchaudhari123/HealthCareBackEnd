package com.healthcare.fraud.fraud;

import com.healthcare.fraud.entity.Claim;
import com.healthcare.fraud.entity.ClaimItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OverBillingRule implements FraudRule {

    @Override
    public int evaluate(Claim claim) {
        BigDecimal sum = claim.getItems()
                .stream()
                .map(ClaimItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.compareTo(claim.getTotalAmount()) > 0 ? 30 : 0;
    }
}
