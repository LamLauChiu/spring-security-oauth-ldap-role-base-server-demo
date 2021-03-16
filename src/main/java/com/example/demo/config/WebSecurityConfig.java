package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfig
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 *  "PasswordEncoder" : Service interface for encoding passwords.
	 *  
	 *  Implementation of PasswordEncoder that uses the BCrypt strong hashing function. Clients
	 *  can optionally supply a "strength" (a.k.a. log rounds in BCrypt) and a SecureRandom
	 *  instance. The larger the strength parameter the more work will have to be done
	 *  (exponentially) to hash the passwords. The default value is 10.
	 * 
	 * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Attempts to authenticate the passed {@link Authentication} object, returning a
	 * fully populated <code>Authentication</code> object (including granted authorities)
	 * if successful.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Assign the endpoint to access the oauth sever
     * 允许匿名访问所有接口 主要是 oauth 接口
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();
//    	http
//		.authorizeRequests()
//			.antMatchers("/**").hasRole("ADMIN")
//			.and()
//		.formLogin();
//    	http.authorizeRequests()
//         .anyRequest().fullyAuthenticated()
//         .and()
//       .formLogin();
    	 http.formLogin()
         .and()
         .authorizeRequests()
         .antMatchers("/**").permitAll();
    }
    
    /*
     *  LDAP 
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth
//		.ldapAuthentication()
//			.userDnPatterns("uid={0},ou=people")
//			.groupSearchBase("ou=groups")
//			.contextSource()
//				.url("ldap://localhost:8389/dc=devglan,dc=com")
//				.and()
//			.passwordCompare()
//				.passwordEncoder(passwordEncoder())
//				.passwordAttribute("userPassword");
//    	
//    	 auth.ldapAuthentication()
//         .userSearchBase("ou=people")
//         .userSearchFilter("(uid={0})")
//         .groupSearchBase("ou=groups")
//         .groupSearchFilter("member={0}")
//         //.ldapAuthoritiesPopulator(ldapAuthoritiesPopulator)
//         .contextSource()
//         .root("dc=mandiri,dc=dis")
//         .ldif("classpath:/data/users.ldif")
//         ;
    	
    	 auth
         .ldapAuthentication()
           .userDnPatterns("uid={0},ou=people")
           .groupSearchBase("ou=groups")
           .contextSource()
             .url("ldap://localhost:8389/dc=springframework,dc=org")
	    	 .and()
	         .passwordCompare()
	           .passwordEncoder(new BCryptPasswordEncoder())
	           .passwordAttribute("userPassword");
    }
}
