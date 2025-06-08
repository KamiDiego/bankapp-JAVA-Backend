// backend/src/main/java/banking2025com/example/bankapp/controller/AccountRestController.java
package banking2025com.example.bankapp.controller;

import banking2025com.example.bankapp.dto.AccountDTO;
import banking2025com.example.bankapp.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    /** GET /api/accounts → list all accounts */
    @GetMapping
    public List<AccountDTO> listAccounts() {
        return accountService.listAccounts();
    }

    /** POST /api/accounts → create new account */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody @Valid AccountDTO dto) {
        return accountService.createAccount(dto.getHolderName(), dto.getBalance());
    }
}



