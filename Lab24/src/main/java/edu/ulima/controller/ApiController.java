package edu.ulima.controller;

import edu.ulima.entidad.Producto;
import edu.ulima.persistencia.ProductoRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/*
API 
- CRUD
           HTTP Verbo   SQL
Create -        POST   - Insert
Read            GET    - SELECT
Update          PUT    - UPDATE
Delete          DELETE - DELETE
*/

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    
    @Autowired
    private ProductoRepositorio productoRepo;
    
    @GetMapping("/listarProductos")
    public List<Producto> listaProducto() {
        List<Producto> lp = productoRepo.findAll();
        return lp;
    }
    
    @GetMapping("/listarProductos/{id}")
    public ResponseEntity<Producto> listaProductoById( @PathVariable(value="id") String codigo)
                         throws ResponseStatusException {
        Producto p = productoRepo.findByCodigo(codigo);
        
        if ( p == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Codigo no existe " + codigo);
        }
        
        return ResponseEntity.ok().body(p);
        
    }
    
    @DeleteMapping("/eliminarProductos/{id}")
    public Map<String,Boolean> eliminarProducto( @PathVariable(value="id") String codigo) 
            throws ResponseStatusException {
        Producto p = productoRepo.findByCodigo(codigo);
        
        if ( p == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Codigo no existe " + codigo);
        }  
        
        productoRepo.delete(p);
        Map<String,Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
        
    }
    
    @PostMapping("/grabarProducto")
    public Producto crearProducto( @RequestBody Producto p) {
        
        return productoRepo.save(p);        
    }
    
   @PutMapping("/actualizarProductos/{id}")
    public ResponseEntity<Producto> actualizarProducto( 
            @PathVariable(value="id") String codigo,
            @RequestBody Producto pa) 
            throws ResponseStatusException {
        Producto p = productoRepo.findByCodigo(codigo);
        
        if ( p == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Codigo no existe " + codigo);
        }  
        
        p.setNombre( pa.getNombre() );
        p.setPrecio( pa.getPrecio() );
        
        productoRepo.save(p);
        return ResponseEntity.ok(p);
        
    }
    
}
