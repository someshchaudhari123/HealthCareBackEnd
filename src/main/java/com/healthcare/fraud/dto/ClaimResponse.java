package com.healthcare.fraud.dto;

public class ClaimResponse {
    public String claimNumber;
    public String status;

    public ClaimResponse(String claimNumber, String status) {
        this.claimNumber = claimNumber;
        this.status = status;
    }
}
