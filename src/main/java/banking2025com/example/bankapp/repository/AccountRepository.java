// backend/src/main/java/banking2025com/example/bankapp/repository/AccountRepository.java
package banking2025com.example.bankapp.repository;

import banking2025com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // If you need custom queries (e.g. findByHolderName), add them here.
}
