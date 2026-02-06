package com.healthcare.fraud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fraud_results")
@Getter
@Setter
public class FraudResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int fraudScore;
    private String riskLevel;

    @OneToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;
}
