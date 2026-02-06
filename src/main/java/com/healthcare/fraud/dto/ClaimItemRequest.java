package com.healthcare.fraud.dto;

import java.math.BigDecimal;

public class ClaimItemRequest {
    public String treatmentCode;
    public String description;
    public BigDecimal amount;
}
