// backend/src/main/java/banking2025com/example/bankapp/dto/TransactionDTO.java
package banking2025com.example.bankapp.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}

