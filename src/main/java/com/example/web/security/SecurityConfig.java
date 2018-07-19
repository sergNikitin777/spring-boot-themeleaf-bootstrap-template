package com.example.web.security;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@RequiredArgsConstructor
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
securedEnabled = true,
jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final DataSource dataSource;
    private final UserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
     // @formatter:off
            http.csrf().disable()
                .authorizeRequests()
                    //.antMatchers("/login").permitAll()
                    //.antMatchers("/api/**").permitAll()
                    //.antMatchers("/v2/**").permitAll()
                    //.antMatchers("/admin","/admin/**").hasRole("ADMIN")
                    //.antMatchers("/**").hasRole("USER")
                    //.antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers("/**").permitAll()
                    //.antMatchers("/webjars/**").permitAll()
                    //.antMatchers("/swagger-ui.html**").permitAll()
                    .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginProcessingUrl("/login-check")
                    .loginPage("/login")
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    .defaultSuccessUrl("/index")
                    .failureUrl("/login?error").permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","remember-me")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
            .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository());

     // @formatter:on
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();
        repositoryImpl.setDataSource(dataSource);
        return repositoryImpl;
    }

    /**
     * password md5 encode(например, user1->24c9e15e52afc47c225b757e7bee1f9d)
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        ReflectionSaltSource rss = new ReflectionSaltSource();
        rss.setUserPropertyToUse("salt");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setSaltSource(rss);
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(new Md5PasswordEncoder());
        auth.authenticationProvider(provider);
        //auth.userDetailsService(customUserDetailsService);

    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

}
