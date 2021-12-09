package edu.ulima;

import edu.ulima.persistencia.UsuarioRepositorio;
import edu.ulima.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"edu.ulima"} )
public class DummyLoad  implements CommandLineRunner {

    @Autowired
    private UsuarioRepositorio repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
       
        repo.save(  new Usuario("empleado01", true, passwordEncoder.encode("1234"), "ROLE_EMPLOYEE" ) ); 
        repo.save(  new Usuario("manager01", true, passwordEncoder.encode("1234"), "ROLE_MANAGER" ) ); 
        
        repo.findAll().forEach( System.out::println);
    }
        
/*    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/
}
