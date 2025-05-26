package com.exaltit.candidature.bankaccountkata.core.port;

import com.exaltit.candidature.bankaccountkata.core.model.Account;

import java.util.Optional;

public interface BankAccountRepository {


    Optional<Account> byNumber(String accountNumber);

    void save(Account bankAccount);

}
