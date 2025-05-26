package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.BankAccount;
import com.exaltit.candidature.bankaccountkata.core.model.BankAccountNotFoundException;
import com.exaltit.candidature.bankaccountkata.core.port.BankAccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreditAccountHandler {

    private final BankAccountRepository bankAccountRepository;

    public void execute(Command command) {
        BankAccount bankAccount = bankAccountRepository.byNumber(command.accountNumber()).orElseThrow(BankAccountNotFoundException::new);
        bankAccount.credit(command.amountToCredit());
        bankAccountRepository.save(bankAccount);
    }


    public record Command(String accountNumber, long amountToCredit) {

    }

}

