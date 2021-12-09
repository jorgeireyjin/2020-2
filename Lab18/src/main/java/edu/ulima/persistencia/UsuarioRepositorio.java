package edu.ulima.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ulima.entidad.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    public Usuario findByUsuario(String usr); 

}