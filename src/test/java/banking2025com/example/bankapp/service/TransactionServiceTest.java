// backend/src/test/java/banking2025com/example/bankapp/service/TransactionServiceTest.java
package banking2025com.example.bankapp.service;

import banking2025com.example.bankapp.dto.TransactionDTO;
import banking2025com.example.bankapp.entity.Account;
import banking2025com.example.bankapp.entity.Transaction;
import banking2025com.example.bankapp.repository.AccountRepository;
import banking2025com.example.bankapp.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepo;

    @Mock
    private TransactionRepository txRepo;

    @Mock
    private ModelMapper mapper;    // ← this mock must be here

    @InjectMocks
    private TransactionService service;  // now has accountRepo, txRepo, mapper injected

    @Test
    void transfer_succeeds() {
        // Arrange
        Account a1 = Account.builder().id(1L).holderName("A").balance(BigDecimal.valueOf(200)).build();
        Account a2 = Account.builder().id(2L).holderName("B").balance(BigDecimal.valueOf(50)).build();

        when(accountRepo.findById(1L)).thenReturn(Optional.of(a1));
        when(accountRepo.findById(2L)).thenReturn(Optional.of(a2));

        Transaction savedTx = Transaction.builder()
                .id(10L)
                .fromAccount(a1)
                .toAccount(a2)
                .amount(BigDecimal.valueOf(30))
                .build();

        when(txRepo.save(any(Transaction.class))).thenReturn(savedTx);

        // Stub the mapper to convert your entity into a DTO
        when(mapper.map(savedTx, TransactionDTO.class)).thenAnswer(invocation -> {
            Transaction txArg = invocation.getArgument(0);
            TransactionDTO dto = new TransactionDTO();
            dto.setId(txArg.getId());
            dto.setFromAccountId(txArg.getFromAccount().getId());
            dto.setToAccountId(txArg.getToAccount().getId());
            dto.setAmount(txArg.getAmount());
            return dto;
        });

        // Act
        TransactionDTO result = service.transfer(1L, 2L, BigDecimal.valueOf(30));

        // Assert DTO fields
        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getFromAccountId()).isEqualTo(1L);
        assertThat(result.getToAccountId()).isEqualTo(2L);
        assertThat(result.getAmount()).isEqualByComparingTo("30");

        // Assert balances were updated in-place
        assertThat(a1.getBalance()).isEqualByComparingTo("170");
        assertThat(a2.getBalance()).isEqualByComparingTo("80");
    }

    @Test
    void transfer_insufficientFunds_throws() {
        // Arrange: account with too little balance
        Account poor = Account.builder().id(1L).holderName("Poor").balance(BigDecimal.valueOf(5)).build();
        Account rich = Account.builder().id(2L).holderName("Rich").balance(BigDecimal.valueOf(100)).build();
        when(accountRepo.findById(1L)).thenReturn(Optional.of(poor));
        when(accountRepo.findById(2L)).thenReturn(Optional.of(rich));

        // Act & Assert
        assertThatThrownBy(() -> service.transfer(1L, 2L, BigDecimal.valueOf(10)))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Insufficient funds");
    }
}

