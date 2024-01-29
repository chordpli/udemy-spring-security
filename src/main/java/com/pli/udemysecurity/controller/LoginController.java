package com.pli.udemysecurity.controller;

import com.pli.udemysecurity.model.Customer;
import com.pli.udemysecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
    Customer savedCustomer = null;
    ResponseEntity response = null;
    try {
      String hashPwd = passwordEncoder.encode(customer.getPassword());
      customer.setPassword(hashPwd);
      savedCustomer = customerRepository.save(customer);
      if (savedCustomer.getId() > 0) {
        response =
            ResponseEntity.status(HttpStatus.CREATED)
                .body("Given user details are successfully registered");
      }
    } catch (Exception exception) {
      response =
          ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occured due to" + exception.getMessage());
    }
    return response;
  }
}
