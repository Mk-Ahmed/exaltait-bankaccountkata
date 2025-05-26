package com.exaltit.candidature.bankaccountkata.core.port;

import com.exaltit.candidature.bankaccountkata.core.model.BankAccount;

import java.util.Optional;

public interface BankAccountRepository {


    Optional<BankAccount> byNumber(String accountNumber);

    void save(BankAccount bankAccount);

}
