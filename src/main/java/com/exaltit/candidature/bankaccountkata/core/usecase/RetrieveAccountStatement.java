package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.AccountStatement;
import com.exaltit.candidature.bankaccountkata.core.port.AccountStatementLoader;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class RetrieveAccountStatement {

    private final Clock clock;
    private final AccountStatementLoader accountStatementLoader;

    public AccountStatement execute(String accountNumber) {
        LocalDateTime now = LocalDateTime.now(clock);
        return accountStatementLoader.by(accountNumber, now).orElseThrow();
    }

}
