package com.exaltit.candidature.bankaccountkata.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class BankAccount {

    @Getter
    private final String number;
    private long amount;


    public void credit(long amount) {
        this.amount += amount;
    }
}
