package banking2025com.example.bankapp.controller;

import banking2025com.example.bankapp.dto.TransactionDTO;
import banking2025com.example.bankapp.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {

    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDTO> listTransactions() {
        return transactionService.listTransactions();
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO transfer(@RequestBody @Valid TransactionDTO dto) {
        return transactionService.transfer(
                dto.getFromAccountId(),
                dto.getToAccountId(),
                dto.getAmount()
        );
    }
}


