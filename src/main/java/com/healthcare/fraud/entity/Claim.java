package com.healthcare.fraud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "claims")
@Getter @Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimNumber;
    private Double amount;
    private String status;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private Patient patient;
}
