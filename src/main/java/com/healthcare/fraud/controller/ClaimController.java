package com.healthcare.fraud.controller;

import com.healthcare.fraud.dto.*;
import com.healthcare.fraud.service.ClaimService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasRole('PROVIDER')")
    public ClaimResponse submit(@RequestBody ClaimRequest request) {
        return claimService.submitClaim(request);
    }
}
