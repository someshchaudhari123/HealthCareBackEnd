package com.healthcare.fraud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    @Column(unique = true, nullable = false)
    private String providerCode;

    public String getProviderCode() {
        return providerCode;
    }
}
