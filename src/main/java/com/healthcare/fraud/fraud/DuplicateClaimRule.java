package com.healthcare.fraud.fraud;

import com.healthcare.fraud.entity.Claim;
import com.healthcare.fraud.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DuplicateClaimRule implements FraudRule {

    private final ClaimRepository claimRepository;

    @Override
    public int evaluate(Claim claim) {

        if (claim.getPatient() == null) {
            return 0;
        }

        boolean exists =
                claimRepository.existsByPatient_PatientIdAndTotalAmount(
                        claim.getPatient().getPatientId(),
                        claim.getTotalAmount()
                );

        return exists ? 40 : 0;
    }
}
