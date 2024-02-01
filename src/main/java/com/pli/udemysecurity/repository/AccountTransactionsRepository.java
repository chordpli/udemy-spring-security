package com.pli.udemysecurity.repository;

import com.pli.udemysecurity.model.AccountTransactions;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {

  List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);
}
