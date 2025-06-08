// backend/src/main/java/banking2025com/example/bankapp/repository/TransactionRepository.java
package banking2025com.example.bankapp.repository;

import banking2025com.example.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // List all transactions sorted by timestamp descending
    List<Transaction> findAllByOrderByTimestampDesc();

    // Get history for a specific account (either as sender or receiver)
    List<Transaction> findByFromAccountIdOrToAccountIdOrderByTimestampDesc(Long fromId, Long toId);
}
