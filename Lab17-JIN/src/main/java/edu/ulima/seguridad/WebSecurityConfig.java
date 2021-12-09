package edu.ulima.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   UserDetailsServiceImpl userDetailsService;

   
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      return bCryptPasswordEncoder;
   }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   }

   
   @Override
   protected void configure(HttpSecurity http) throws Exception {

       System.out.println("===>>>>>>  WebSecurityConfig H2-console ....");
       
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        
      // Configuration for Login Form.
      http.authorizeRequests()
      .and()
      .formLogin()
            .loginPage("/admin/login")                     //
            .defaultSuccessUrl("/admin/usuarioInfo")      //
            .failureUrl("/admin/login?error=true")        //
            .usernameParameter("userName")               //
            .passwordParameter("password")

            .and()
            .logout()
            .logoutUrl("/admin/logout")
            .logoutSuccessUrl("/");
      
   }

}

