package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.Account;
import com.exaltit.candidature.bankaccountkata.core.model.BankAccountNotFoundException;
import com.exaltit.candidature.bankaccountkata.core.port.BankAccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreditAccountHandler {

    private final BankAccountRepository bankAccountRepository;

    public void execute(Command command) {
        Account account = bankAccountRepository.byNumber(command.accountNumber()).orElseThrow(BankAccountNotFoundException::new);
        account.credit(command.amountToCredit());
        bankAccountRepository.save(account);
    }


    public record Command(String accountNumber, long amountToCredit) {

    }

}

