package edu.ulima.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Cifrador {
    
    @Bean 
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
