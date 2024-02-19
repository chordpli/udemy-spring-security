package com.pli.udemysecurity.controller;

import com.pli.udemysecurity.model.Loans;
import com.pli.udemysecurity.repository.LoanRepository;
import java.util.List;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

  private LoanRepository loanRepository;

  @GetMapping("/myLoans")
  @PostAuthorize("hasRole('ROOT')")
  public List<Loans> getLoanDetails(@RequestParam int id) {
    return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
  }
}
