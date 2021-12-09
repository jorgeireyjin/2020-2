package edu.ulima;

import edu.ulima.entidad.Producto;
import edu.ulima.entidad.Usuario;
import edu.ulima.persistencia.ProductoRepositorio;
import edu.ulima.persistencia.UsuarioRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DummyLoad implements CommandLineRunner {

    @Autowired
    private UsuarioRepositorio repo;

    @Autowired
    private ProductoRepositorio repo2;
    
    @Autowired
    private PasswordEncoder pwEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Aqui se ejecuta la logica principal
        // INSERT - save
        // DELETE - remove
        // UPDATE  - merge
        // SELECT find - select
        repo.save( new Usuario("empleado01", true, pwEncoder.encode("1234"), "ROLE_EMPLOYEE" ) );
        repo.save( new Usuario("manager01", true, pwEncoder.encode("1234"), "ROLE_MANAGER" ) );
        
        System.out.println("============================");
        repo2.save( new Producto("A-001","Producto A1", 1.0, new Date() ) );
        repo2.save( new Producto("A-002","Producto A2", 2.0, new Date() ) );
        repo2.save( new Producto("A-003","Producto A3", 3.0, new Date() ) );
        repo2.save( new Producto("A-004","Producto A4", 4.0, new Date() ) );
        repo2.save( new Producto("A-005","Producto A5", 5.0, new Date() ) );
        repo2.save( new Producto("A-006","Producto A6", 6.0, new Date() ) );
        repo2.save( new Producto("A-007","Producto A7", 7.0, new Date() ) );
        repo2.save( new Producto("A-008","Producto A8", 8.0, new Date() ) );
        repo2.save( new Producto("A-009","Producto A9", 9.0, new Date() ) );
        repo2.save( new Producto("B-001","Producto B1", 1.0, new Date() ) );
        repo2.save( new Producto("B-002","Producto B2", 2.0, new Date() ) );
        repo2.save( new Producto("B-003","Producto B3", 3.0, new Date() ) );
        repo2.save( new Producto("B-004","Producto B4", 4.0, new Date() ) );
        repo2.save( new Producto("B-005","Producto B5", 5.0, new Date() ) ); 
        
        repo2.findAll().forEach( System.out::println );
        
        System.out.println("============================");
        repo2.findByPrecioMenorQue(3.0).forEach( System.out::println );
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
