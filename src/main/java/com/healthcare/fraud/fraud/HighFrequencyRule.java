package com.healthcare.fraud.fraud;

import com.healthcare.fraud.entity.Claim;
import com.healthcare.fraud.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HighFrequencyRule implements FraudRule {

    private final ClaimRepository claimRepository;

    @Override
    public int evaluate(Claim claim) {

        if (claim.getPatient() == null) {
            return 0;
        }

        long count =
                claimRepository.countByPatient_PatientId(
                        claim.getPatient().getPatientId()
                );

        return count > 3 ? 20 : 0;
    }
}
