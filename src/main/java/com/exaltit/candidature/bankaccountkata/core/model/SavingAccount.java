package com.exaltit.candidature.bankaccountkata.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SavingAccount implements Account {

    private final String number;
    private final long balanceLimit;
    private long amount;

    @Override
    public String number() {
        return number;
    }

    @Override
    public void credit(long amount) {
        if (amount + this.amount > balanceLimit) throw new AccountLimitReachedException();
        this.amount += amount;
    }

    @Override
    public void debit(long amount) {
        if (amount > this.amount) throw new InsufficientFundsException();
        this.amount -= amount;
    }

}
