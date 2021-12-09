package edu.ulima.persistencia;

import edu.ulima.entidad.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    
    public Usuario findByUsuario(String usr); 

}