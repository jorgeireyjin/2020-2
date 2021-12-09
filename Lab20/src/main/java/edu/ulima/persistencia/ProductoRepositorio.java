package edu.ulima.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ulima.entidad.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    
    public Producto findByCodigo(String codigo);

    @Query("SELECT p FROM EPRODUCTO p WHERE p.precio < ?1")
    public List<Producto> findByPrecioLessThan(double precio);
    
}