package com.healthcare.fraud.dto;

import java.util.List;

public class ClaimRequest {
    public Long providerId;
    public Long patientId;
    public List<ClaimItemRequest> items;
}
