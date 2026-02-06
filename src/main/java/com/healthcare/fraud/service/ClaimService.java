package com.healthcare.fraud.service;

import com.healthcare.fraud.dto.*;
import com.healthcare.fraud.entity.*;
import com.healthcare.fraud.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClaimService {

    private final ClaimRepository claimRepo;
    private final ProviderRepository providerRepo;
    private final PatientRepository patientRepo;

    public ClaimService(ClaimRepository claimRepo,
                        ProviderRepository providerRepo,
                        PatientRepository patientRepo) {
        this.claimRepo = claimRepo;
        this.providerRepo = providerRepo;
        this.patientRepo = patientRepo;
    }

    public ClaimResponse submitClaim(ClaimRequest request) {

        if (request.items == null || request.items.isEmpty()) {
            throw new IllegalArgumentException("Claim items required");
        }

        Provider provider = providerRepo.findById(request.providerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider"));

        Patient patient = patientRepo.findById(request.patientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient"));

        BigDecimal totalAmount = request.items.stream()
                .map(item -> {
                    if (item.amount == null || item.amount.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("Invalid item amount");
                    }
                    return item.amount;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Claim claim = new Claim();
        claim.setClaimNumber("CLM-" + UUID.randomUUID());
        claim.setProvider(provider);
        claim.setPatient(patient);
        claim.setTotalAmount(totalAmount);
        claim.setStatus("SUBMITTED");
        claim.setSubmittedAt(LocalDateTime.now());

        claim.setItems(
                request.items.stream().map(itemReq -> {
                    ClaimItem item = new ClaimItem();
                    item.setTreatmentCode(itemReq.treatmentCode);
                    item.setDescription(itemReq.description);
                    item.setAmount(itemReq.amount);
                    item.setClaim(claim);
                    return item;
                }).collect(Collectors.toList())
        );

        claimRepo.save(claim);

        return new ClaimResponse(claim.getClaimNumber(), claim.getStatus());
    }
}
