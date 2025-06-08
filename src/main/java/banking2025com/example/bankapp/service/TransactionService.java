// backend/src/main/java/banking2025com/example/bankapp/service/TransactionService.java
package banking2025com.example.bankapp.service;

import banking2025com.example.bankapp.dto.TransactionDTO;
import banking2025com.example.bankapp.entity.Account;
import banking2025com.example.bankapp.entity.Transaction;
import banking2025com.example.bankapp.repository.AccountRepository;
import banking2025com.example.bankapp.repository.TransactionRepository;
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
public class TransactionService {
    private final TransactionRepository txRepo;
    private final AccountRepository accountRepo;
    private final ModelMapper mapper;

    /** List all transactions as DTOs */
    public List<TransactionDTO> listTransactions() {
        return txRepo.findAll()
                .stream()
                .map(tx -> mapper.map(tx, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Transfer funds between two accounts and record a transaction.
     * @return the created TransactionDTO
     */
    @Transactional
    public TransactionDTO transfer(Long fromId, Long toId, BigDecimal amount) {
        if (fromId.equals(toId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot transfer to the same account");
        }

        Account fromAcc = accountRepo.findById(fromId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Source account not found"));
        Account toAcc = accountRepo.findById(toId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination account not found"));

        if (fromAcc.getBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        // update balances
        fromAcc.setBalance(fromAcc.getBalance().subtract(amount));
        toAcc.setBalance(toAcc.getBalance().add(amount));
        accountRepo.save(fromAcc);
        accountRepo.save(toAcc);

        // build and save transaction with entity references
        Transaction tx = Transaction.builder()
                .fromAccount(fromAcc)
                .toAccount(toAcc)
                .amount(amount)
                .build();
        Transaction saved = txRepo.save(tx);

        return mapper.map(saved, TransactionDTO.class);
    }
}



