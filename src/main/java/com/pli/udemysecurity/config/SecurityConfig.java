package com.pli.udemysecurity.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(
            (request) ->
                request
                    .requestMatchers("/myAccount", "/myBalance", "myLoans", "myCards")
                    .authenticated()
                    .requestMatchers("/notices", "/contact")
                    .permitAll())
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .build();
  }

  //  @Bean
  //  public InMemoryUserDetailsManager userDetailsService() {
  //
  //    UserDetails admin =
  // User.withUsername("admin").password("1234").authorities("ADMIN").build();
  //    UserDetails user = User.withUsername("user").password("1234").authorities("read").build();
  //
  //    return new InMemoryUserDetailsManager(admin, user);
  //  }

  @Bean
  public UserDetailsService userDetailsService(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
