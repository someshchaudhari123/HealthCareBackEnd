package com.healthcare.fraud.repository;

import com.healthcare.fraud.entity.ClaimItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimItemRepository extends JpaRepository<ClaimItem, Long> {}