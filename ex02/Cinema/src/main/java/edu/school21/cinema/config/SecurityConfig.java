package edu.school21.cinema.config;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSession;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import edu.school21.cinema.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final UserSessionService sessionService;

    @Autowired
    public SecurityConfig(UserService userService,
                          UserSessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Bean
    @Autowired
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/profile").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                .antMatchers("/", "/signIn", "/signUp").permitAll()
                .mvcMatchers("/confirm/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .formLogin().permitAll()
                .loginPage("/signIn")
                .loginProcessingUrl("/signIn")
                .usernameParameter("email")
                .passwordParameter("pass")
                .successHandler(((request, response, authentication) -> {
                    System.out.println(authentication.getName());
                    Optional<User> user = userService.findByEmail(authentication.getName());
                    request.getSession().setAttribute("UserAttributes", user.get());
                    String remoteAddr = request.getRemoteAddr();
                    if (remoteAddr.equals("0:0:0:0:0:0:0:1")) {
                        InetAddress localIp = InetAddress.getLocalHost();
                        remoteAddr = localIp.getHostAddress();
                    }
                    UserSession session = new UserSession(null, remoteAddr, LocalDateTime.now(), user.get().getId());
                    sessionService.add(session);
                    request.getSession().setAttribute("SessionAttributes", sessionService.getAllByUserId(user.get().getId()));
                    if (user.get().getRole() == Role.ADMIN) {
                        response.sendRedirect("/admin/panel/halls");
                    } else {
                        response.sendRedirect("/profile");
                    }
                }))
                .failureUrl("/signIn")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .rememberMe().rememberMeParameter("rememberMe")
                .tokenValiditySeconds(1800).key("topSecret");
        return http.build();
    }
}
