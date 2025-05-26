package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.BankAccount;
import com.exaltit.candidature.bankaccountkata.core.model.BankAccountNotFoundException;
import com.exaltit.candidature.bankaccountkata.core.port.BankAccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DebitAccountHandler {

    private final BankAccountRepository accountRepository;

    public void execute(Command command) {
        BankAccount bankAccount = accountRepository.byNumber(command.accountNumber()).orElseThrow(BankAccountNotFoundException::new);
        bankAccount.debit(command.amountToDebit);
        accountRepository.save(bankAccount);
    }

    public record Command(String accountNumber, long amountToDebit) {

    }

}
