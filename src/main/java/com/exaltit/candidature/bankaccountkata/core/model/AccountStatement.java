package com.exaltit.candidature.bankaccountkata.core.model;

import java.util.List;

public record AccountStatement(
        String accountType,
        long currentBalance,
        List<Transaction> transactions
) {
}
