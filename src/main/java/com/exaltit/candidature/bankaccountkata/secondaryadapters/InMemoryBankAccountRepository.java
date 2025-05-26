package com.exaltit.candidature.bankaccountkata.secondaryadapters;

import com.exaltit.candidature.bankaccountkata.core.model.Account;
import com.exaltit.candidature.bankaccountkata.core.port.BankAccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryBankAccountRepository implements BankAccountRepository {

    private final Map<String, Account> store = new HashMap<>();

    @Override
    public Optional<Account> byNumber(String accountNumber) {
        return Optional.ofNullable(store.get(accountNumber));
    }

    @Override
    public void save(Account bankAccount) {
        store.put(bankAccount.number(), bankAccount);
    }


}
