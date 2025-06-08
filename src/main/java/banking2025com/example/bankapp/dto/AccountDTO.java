// backend/src/main/java/banking2025com/example/bankapp/dto/AccountDTO.java
package banking2025com.example.bankapp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Holder name is required")
    private String holderName;

    @DecimalMin(value = "0.00", inclusive = true, message = "Balance must be ≥ 0")
    private BigDecimal balance;
}
