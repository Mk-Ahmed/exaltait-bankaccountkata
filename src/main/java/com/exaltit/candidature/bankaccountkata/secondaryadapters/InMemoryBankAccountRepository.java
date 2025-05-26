package com.exaltit.candidature.bankaccountkata.secondaryadapters;

import com.exaltit.candidature.bankaccountkata.core.model.BankAccount;
import com.exaltit.candidature.bankaccountkata.core.port.BankAccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryBankAccountRepository implements BankAccountRepository {

    private final Map<String, BankAccount> store = new HashMap<>();

    @Override
    public Optional<BankAccount> byNumber(String accountNumber) {
        return Optional.ofNullable(store.get(accountNumber));
    }

    @Override
    public void save(BankAccount bankAccount) {
        store.put(bankAccount.getNumber(), bankAccount);
    }


}
