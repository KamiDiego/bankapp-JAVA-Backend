package banking2025com.example.bankapp.controller;

import banking2025com.example.bankapp.dto.AccountDTO;
import banking2025com.example.bankapp.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;                        // Mockito import
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountRestController.class)
class AccountRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService svc;

    @Test
    void listAccounts_returnsJson() throws Exception {
        // Arrange: mock service to return one sample AccountDTO
        AccountDTO sample = new AccountDTO();
        sample.setId(1L);
        sample.setHolderName("TestUser");
        sample.setBalance(BigDecimal.valueOf(123.45));

        when(svc.listAccounts()).thenReturn(Collections.singletonList(sample));

        // Act & Assert: GET /api/accounts returns a JSON array with our sample
        mvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].holderName").value("TestUser"))
                .andExpect(jsonPath("$[0].balance").value(123.45));
    }
}


