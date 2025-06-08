// backend/src/main/java/banking2025com/example/bankapp/dto/TransferRequestDTO.java
package banking2025com.example.bankapp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    @NotNull(message = "Sender account ID required")
    private Long fromAccountId;

    @NotNull(message = "Recipient account ID required")
    private Long toAccountId;

    @NotNull(message = "Amount required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be positive")
    private BigDecimal amount;
}
