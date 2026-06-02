package com.gstech.saas.accounting.reports.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssessmentHistoryRow(
        Long invoiceId,
        String associationName,
        String unitNumber,
        String ownerName,
        LocalDate invoiceDate,
        LocalDate dueDate,
        BigDecimal amount,
        String status
) {}