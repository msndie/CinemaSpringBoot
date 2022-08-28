package edu.school21.cinema.config;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSession;
import edu.school21.cinema.security.MyAuthenticationFailureHandler;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import edu.school21.cinema.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserSessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          UserService userService,
                          UserSessionService sessionService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/profile").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                .antMatchers("/", "/signIn", "/signUp").permitAll()
                .anyRequest().authenticated();
        http
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
                .failureUrl("/signIn");
        http.csrf().disable();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//    }
}
