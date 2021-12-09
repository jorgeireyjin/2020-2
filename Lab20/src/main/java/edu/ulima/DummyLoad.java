package edu.ulima;

import edu.ulima.entidad.Producto;
import edu.ulima.persistencia.UsuarioRepositorio;
import edu.ulima.entidad.Usuario;
import edu.ulima.persistencia.ProductoRepositorio;
import java.util.Date;
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
    private ProductoRepositorio productorepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
       
        //-----------------------------------------------------------------------------
        //***** CARGAR USUARIOS DEMO ****
        //-----------------------------------------------------------------------------
        repo.save(  new Usuario("empleado01", true, passwordEncoder.encode("1234"), "ROLE_EMPLOYEE" ) ); 
        repo.save(  new Usuario("manager01", true, passwordEncoder.encode("1234"), "ROLE_MANAGER" ) ); 
        
        repo.findAll().forEach( System.out::println);
        
        //-----------------------------------------------------------------------------
        //***** CARGAR LOS PRODUCTOS ****
        //-----------------------------------------------------------------------------
        productorepo.save( new Producto("A-001","Producto A1" , 1.0 , new Date() ) );
        productorepo.save( new Producto("A-002","Producto A2" , 2.0 , new Date() ) );
        productorepo.save( new Producto("A-003","Producto A3" , 3.0 , new Date() ) );
        productorepo.save( new Producto("A-004","Producto A4" , 4.0 , new Date() ) );
        productorepo.save( new Producto("A-005","Producto A5" , 5.0 , new Date() ) );
        productorepo.save( new Producto("A-006","Producto A6" , 6.0 , new Date() ) );
        productorepo.save( new Producto("A-007","Producto A7" , 7.0 , new Date() ) );
        productorepo.save( new Producto("A-008","Producto A8" , 8.0 , new Date() ) );
        productorepo.save( new Producto("A-009","Producto A9" , 9.0 , new Date() ) );
        productorepo.save( new Producto("B-001","Producto B1" , 1.0 , new Date() ) );
        productorepo.save( new Producto("B-002","Producto B2" , 2.0 , new Date() ) );
        productorepo.save( new Producto("B-003","Producto B3" , 3.0 , new Date() ) );
        productorepo.save( new Producto("B-004","Producto B4" , 4.0 , new Date() ) );
        productorepo.save( new Producto("B-005","Producto B5" , 5.0 , new Date() ) );
        
        productorepo.findAll().forEach( System.out::println);

        System.out.println("====================================");
       productorepo.findByPrecioLessThan(3.0).forEach( System.out::println);
    }
        
/*    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/
}
