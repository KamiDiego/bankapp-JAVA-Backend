// backend/src/main/java/banking2025com/example/bankapp/service/AccountService.java
package banking2025com.example.bankapp.service;

import banking2025com.example.bankapp.dto.AccountDTO;
import banking2025com.example.bankapp.entity.Account;
import banking2025com.example.bankapp.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper mapper = new ModelMapper();

    /** Create a new account */
    public AccountDTO createAccount(String holderName, BigDecimal initialBalance) {
        Account account = Account.builder()
                .holderName(holderName)
                .balance(initialBalance)
                .build();
        Account saved = accountRepository.save(account);
        return mapper.map(saved, AccountDTO.class);
    }

    /** List all accounts as DTOs */
    public List<AccountDTO> listAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(acc -> mapper.map(acc, AccountDTO.class))
                .collect(Collectors.toList());
    }

    /** Get an account by ID, as DTO */
    public AccountDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(acc -> mapper.map(acc, AccountDTO.class))
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account not found: " + id
                        )
                );
    }

    /** Update balance */
    @Transactional
    public void updateBalance(Long accountId, BigDecimal newBalance) {
        Account acc = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account not found: " + accountId
                        )
                );
        acc.setBalance(newBalance);
        accountRepository.save(acc);
    }

    /** Delete account */
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
