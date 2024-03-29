package com.pli.udemysecurity.config;

import com.pli.udemysecurity.filter.*;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
    requestHandler.setCsrfRequestAttributeName("_csrf");

    return http.authorizeHttpRequests(
            (request) ->
                request
                    //                    .requestMatchers("/myAccount")
                    //                    .hasAnyAuthority("VIEWACCOUNT")
                    //                    .requestMatchers("/myBalance")
                    //                    .hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
                    //                    .requestMatchers("/myLoans")
                    //                    .hasAuthority("VIEWLOANS")
                    //                    .requestMatchers("/myCards")
                    //                    .hasAuthority("VIEWCARDS")
                    .requestMatchers("/myAccount")
                    .hasRole("User")
                    .requestMatchers("/myBalance")
                    .hasAnyRole("User", "Admin")
                    .requestMatchers("/myLoans")
                    .authenticated()
                    //                    .hasRole("User")
                    .requestMatchers("/myCards")
                    .hasRole("User")
                    .requestMatchers("/user")
                    .authenticated()
                    .requestMatchers("/notices", "/contact", "/register")
                    .permitAll())
        .sessionManagement(
            (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .csrf(
            (csrf) ->
                csrf.csrfTokenRequestHandler(requestHandler)
                    .ignoringRequestMatchers("/contact", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
        .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:4200"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setExposedHeaders(List.of("Authorization"));
    configuration.setAllowCredentials(true);
    configuration.addAllowedHeader("*");
    configuration.setMaxAge(3600L);
    // if exist Token then disable comment this line
    // configuration.addExposedHeader("accessToken");
    // configuration.addExposedHeader("refreshToken");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
