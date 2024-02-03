package com.pli.udemysecurity.repository;

import com.pli.udemysecurity.model.Loans;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {

  List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}