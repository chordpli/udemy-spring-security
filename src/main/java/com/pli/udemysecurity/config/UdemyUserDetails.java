package com.pli.udemysecurity.config;

import com.pli.udemysecurity.model.Customer;
import com.pli.udemysecurity.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UdemyUserDetails implements UserDetailsService {

  private final CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String userName, password = null;
    List<GrantedAuthority> authorities = null;
    List<Customer> customer = customerRepository.findByEmail(username);

    if (customer.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    } else {
      userName = customer.getFirst().getEmail();
      password = customer.getFirst().getPassword();
      authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(customer.getFirst().getRoles()));
    }
    return new User(userName, password, authorities);
  }
}
