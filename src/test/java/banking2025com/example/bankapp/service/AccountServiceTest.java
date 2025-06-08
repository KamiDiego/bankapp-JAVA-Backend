package banking2025com.example.bankapp.service;

import banking2025com.example.bankapp.dto.AccountDTO;
import banking2025com.example.bankapp.entity.Account;
import banking2025com.example.bankapp.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository repo;

    @InjectMocks
    private AccountService service;

    @Test
    void createAccount_happyPath() {
        // arrange: repository will return an Account entity
        Account savedEntity = Account.builder()
                .id(1L)
                .holderName("Test")
                .balance(BigDecimal.valueOf(123.45))
                .build();
        when(repo.save(any(Account.class))).thenReturn(savedEntity);

        // act: service returns AccountDTO
        AccountDTO result = service.createAccount("Test", BigDecimal.valueOf(123.45));

        // assert: DTO has the expected values
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getHolderName()).isEqualTo("Test");
        assertThat(result.getBalance()).isEqualByComparingTo("123.45");
    }

    @Test
    void getAccountById_notFound_throws() {
        // arrange: repository returns empty
        when(repo.findById(99L)).thenReturn(Optional.empty());

        // act & assert: service should throw ResponseStatusException or IllegalArgumentException
        assertThatThrownBy(() -> service.getAccountById(99L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404");
    }
}
