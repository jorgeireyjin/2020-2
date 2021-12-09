package edu.ulima.persistencia;

import edu.ulima.entidad.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepositorio extends MongoRepository<Producto, String> {
    
    public Producto findByCodigo(String codigo);

    
}