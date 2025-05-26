package com.exaltit.candidature.bankaccountkata.core.usecase;

import com.exaltit.candidature.bankaccountkata.core.model.Account;
import com.exaltit.candidature.bankaccountkata.core.model.AccountLimitReachedException;
import com.exaltit.candidature.bankaccountkata.core.model.BankAccountNotFoundException;
import com.exaltit.candidature.bankaccountkata.core.model.CurrentAccount;
import com.exaltit.candidature.bankaccountkata.core.model.SavingAccount;
import com.exaltit.candidature.bankaccountkata.core.usecase.CreditAccountHandler.Command;
import com.exaltit.candidature.bankaccountkata.secondaryadapters.InMemoryBankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreditAccountHandlerTest {

    private static final String ACCOUNT_NUMBER = "123456789";
    private static final String OTHER_ACCOUNT_NUMBER = "987654321";

    private InMemoryBankAccountRepository accountRepository;
    private CreditAccountHandler handler;

    @BeforeEach
    void setup() {
        accountRepository = new InMemoryBankAccountRepository();
        handler = new CreditAccountHandler(accountRepository);
    }

    @Test
    void should_throw_exception_when_account_does_not_exist() {
        accountRepository.save(new CurrentAccount(ACCOUNT_NUMBER, 0, 100L));
        assertThrows(
                BankAccountNotFoundException.class,
                () -> {
                    Command command = new Command(OTHER_ACCOUNT_NUMBER, 100L);
                    handler.execute(command);
                }
        );
    }

    @Test
    void should_credit_amount() {
        accountRepository.save(new CurrentAccount(ACCOUNT_NUMBER, 0, 0));

        handler.execute(new Command(ACCOUNT_NUMBER, 100L));

        Account actual = accountRepository.byNumber(ACCOUNT_NUMBER).orElseThrow();
        assertThat(actual).isEqualTo(new CurrentAccount(ACCOUNT_NUMBER, 0, 100L));
    }

    @Test
    void should_throw_exception_when_account_limit_reached() {
        accountRepository.save(new SavingAccount(ACCOUNT_NUMBER, 1000, 100L));
        assertThrows(
                AccountLimitReachedException.class,
                () -> {
                    Command command = new Command(ACCOUNT_NUMBER, 901);
                    handler.execute(command);
                }
        );
    }
}
