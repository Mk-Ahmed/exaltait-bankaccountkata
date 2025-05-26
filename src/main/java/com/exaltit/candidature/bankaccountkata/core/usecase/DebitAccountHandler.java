package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.Account;
import com.exaltit.candidature.bankaccountkata.core.model.BankAccountNotFoundException;
import com.exaltit.candidature.bankaccountkata.core.port.BankAccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DebitAccountHandler {

    private final BankAccountRepository accountRepository;

    public void execute(Command command) {
        Account account = accountRepository.byNumber(command.accountNumber()).orElseThrow(BankAccountNotFoundException::new);
        account.debit(command.amountToDebit);
        accountRepository.save(account);
    }

    public record Command(String accountNumber, long amountToDebit) {

    }

}
