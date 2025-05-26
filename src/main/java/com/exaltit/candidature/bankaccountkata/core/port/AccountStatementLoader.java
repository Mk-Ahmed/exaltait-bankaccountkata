package com.exaltit.candidature.bankaccountkata.core.port;

import com.exaltit.candidature.bankaccountkata.core.model.AccountStatement;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccountStatementLoader {

    Optional<AccountStatement> by(String accountNumber, LocalDateTime now);
}
