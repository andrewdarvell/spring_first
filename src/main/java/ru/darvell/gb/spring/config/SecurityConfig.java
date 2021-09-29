package ru.darvell.gb.spring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.darvell.gb.spring.component.MyAuthenticationEntryPoint;
import ru.darvell.gb.spring.service.TokenAuthService;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";

    private final TokenAuthService tokenAuthService;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .addFilterBefore(new StatelessAuthFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/product/form", "/product/delete").hasAnyRole(ADMIN, MANAGER)
//                .antMatchers(HttpMethod.POST,"/product").hasAnyRole(ADMIN, MANAGER)
//                .antMatchers("/category/**").hasAnyRole(ADMIN, MANAGER)
//                .antMatchers(ADMIN_URL + "/**").hasRole(ADMIN)
                .anyRequest().permitAll();
    }


}
