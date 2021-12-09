package edu.ulima.persistencia;

import edu.ulima.entidad.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    
    public Producto findByCodigo(String codigo);

    /*
    SELECT * FROM TPRODUCTO WHERE CODIGO = ?
    */
    
    /*
    SQL:   SELECT * FROM TPRODUCTO WHERE precio < 3
    JP-QL: SELECT p FROM EPRODUCTO p WHERE p.precio < ?1
    */
    @Query("SELECT p FROM EPRODUCTO p WHERE p.precio < ?1")
    public List<Producto> findByPrecioMenorQue(double precio);
}
