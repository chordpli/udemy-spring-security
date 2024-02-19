package com.pli.udemysecurity.repository;

import com.pli.udemysecurity.model.Loans;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {

  @PreAuthorize("hasRole('User')")
  List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
