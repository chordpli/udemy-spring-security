package com.pli.udemysecurity.config;

import com.pli.udemysecurity.model.Customer;
import com.pli.udemysecurity.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Component
public class UdemyUserPwdAuthenticationProvider implements AuthenticationProvider {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String pwd = authentication.getCredentials().toString();
    List<Customer> customer = customerRepository.findByEmail(username);

    if (customer.size() > 0) {
      if (passwordEncoder.matches(pwd, customer.getFirst().getPassword())) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customer.getFirst().getRoles()));
        return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
      } else {
        throw new BadCredentialsException("Invalid password");
      }
    } else {
      throw new BadCredentialsException("No user registered with this email");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return false;
  }
}
