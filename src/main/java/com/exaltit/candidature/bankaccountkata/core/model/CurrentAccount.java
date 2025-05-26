package com.exaltit.candidature.bankaccountkata.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CurrentAccount implements Account {

    private final String number;
    private final long overdraftLimit;
    private long amount;

    @Override
    public String number() {
        return number;
    }

    @Override
    public void credit(long amountToCredit) {
        amount += amountToCredit;
    }

    @Override
    public void debit(long amountToDebit) {
        long limit = amount + overdraftLimit;
        if (amountToDebit > limit) throw new InsufficientFundsException();
        amount -= amountToDebit;
    }

}
