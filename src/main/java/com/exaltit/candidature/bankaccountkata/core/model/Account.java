package com.exaltit.candidature.bankaccountkata.core.model;

public interface Account {

    String number();

    void credit(long amount);

    void debit(long amount);


}
