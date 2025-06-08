package banking2025com.example.bankapp.controller;

import banking2025com.example.bankapp.dto.TransactionDTO;
import banking2025com.example.bankapp.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionRestController.class)
class TransactionRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService svc;

    @Test
    void listTransactions_returnsJsonArray() throws Exception {
        TransactionDTO sample = new TransactionDTO();
        sample.setId(1L);
        sample.setFromAccountId(1L);
        sample.setToAccountId(2L);
        sample.setAmount(BigDecimal.valueOf(30));
        sample.setTimestamp(LocalDateTime.now());

        when(svc.listTransactions()).thenReturn(Collections.singletonList(sample));

        mvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fromAccountId").value(1))
                .andExpect(jsonPath("$[0].toAccountId").value(2))
                .andExpect(jsonPath("$[0].amount").value(30));
    }

    @Test
    void transferEndpoint_acceptsAndReturnsJson() throws Exception {
        TransactionDTO sample = new TransactionDTO();
        sample.setId(10L);
        sample.setFromAccountId(1L);
        sample.setToAccountId(2L);
        sample.setAmount(BigDecimal.valueOf(50));
        sample.setTimestamp(LocalDateTime.now());

        when(svc.transfer(1L, 2L, BigDecimal.valueOf(50))).thenReturn(sample);

        String jsonRequest = """
            {
              "fromAccountId": 1,
              "toAccountId": 2,
              "amount": 50
            }
            """;

        mvc.perform(post("/api/transactions/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.amount").value(50));
    }
}
