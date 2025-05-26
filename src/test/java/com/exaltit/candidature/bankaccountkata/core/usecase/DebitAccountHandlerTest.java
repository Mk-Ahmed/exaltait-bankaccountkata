package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.BankAccount;
import com.exaltit.candidature.bankaccountkata.core.model.BankAccountNotFoundException;
import com.exaltit.candidature.bankaccountkata.core.model.InsufficientFundsException;
import com.exaltit.candidature.bankaccountkata.core.usecase.DebitAccountHandler.Command;
import com.exaltit.candidature.bankaccountkata.secondaryadapters.InMemoryBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DebitAccountHandlerTest {


    private static final String ACCOUNT_NUMBER = "123456789";
    private static final String OTHER_ACCOUNT_NUMBER = "987654321";

    private InMemoryBankAccountRepository accountRepository;
    private DebitAccountHandler handler;

    @BeforeEach
    void setup() {
        accountRepository = new InMemoryBankAccountRepository();
        handler = new DebitAccountHandler(accountRepository);
    }

    @Test
    void should_throw_exception_when_account_does_not_exist() {
        accountRepository.save(new BankAccount(ACCOUNT_NUMBER, 0, 100L));
        assertThrows(
                BankAccountNotFoundException.class,
                () -> {
                    Command command = new Command(OTHER_ACCOUNT_NUMBER, 100L);
                    handler.execute(command);
                }
        );
    }

    @Test
    void should_thrown_exception_when_insufficient_funds() {
        accountRepository.save(new BankAccount(ACCOUNT_NUMBER, 0, 10));
        assertThrows(
                InsufficientFundsException.class,
                () -> {
                    Command command = new Command(ACCOUNT_NUMBER, 20);
                    handler.execute(command);
                }
        );
    }

    @Test
    void should_debit_amount_when_sufficient_funds_available() {
        accountRepository.save(new BankAccount(ACCOUNT_NUMBER, 0, 100L));

        handler.execute(new Command(ACCOUNT_NUMBER, 100L));

        BankAccount actual = accountRepository.byNumber(ACCOUNT_NUMBER).orElseThrow();
        assertThat(actual).isEqualTo(new BankAccount(ACCOUNT_NUMBER, 0, 0));
    }

    @Test
    void should_debit_amount_when_balance_plus_overdraft_covers_amount() {
        accountRepository.save(new BankAccount(ACCOUNT_NUMBER, 10, 100L));

        handler.execute(new Command(ACCOUNT_NUMBER, 110L));

        BankAccount actual = accountRepository.byNumber(ACCOUNT_NUMBER).orElseThrow();
        assertThat(actual).isEqualTo(new BankAccount(ACCOUNT_NUMBER, 10, -10));
    }

}
