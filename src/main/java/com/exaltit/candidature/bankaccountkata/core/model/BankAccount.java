package com.exaltit.candidature.bankaccountkata.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class BankAccount {

    @Getter
    private final String number;
    private final long overdraftLimit;
    private long amount;

    public void credit(long amountToCredit) {
        amount += amountToCredit;
    }

    public void debit(long amountToDebit) {
        long limit = amount + overdraftLimit;
        if (amountToDebit > limit) throw new InsufficientFundsException();
        amount -= amountToDebit;
    }
}
