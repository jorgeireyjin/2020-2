package edu.ulima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import edu.ulima.clienterest.Producto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    
    @RequestMapping( value="/", method=RequestMethod.GET)
    public String home() {
        return "index";
    }
    
     @RequestMapping( value="/consultarXCodigo", method=RequestMethod.POST)
     @ResponseBody
    public String consultar( @RequestParam("codigo") String cod) {
        String url = "https://agile-plains-29293.herokuapp.com/api/v1/listarProductos/" + cod;
        
        RestTemplate rt =  new RestTemplate();
        Producto p = rt.getForObject(url, Producto.class);
        
        // Manejar la data para que se vea bonito en un template ....
        
        return p.toString();
        
    }
    
     @RequestMapping( value="/consultarTodos", method=RequestMethod.POST)
     @ResponseBody    
    public  ResponseEntity<Producto[]> consultarTodos() {
        String url = "https://agile-plains-29293.herokuapp.com/api/v1/listarProductos";
        
        RestTemplate rt =  new RestTemplate();       
        
        Producto[] ap = rt.getForObject(url, Producto[].class);
        
        return ResponseEntity.ok().body(ap);
    }
    
    
    @RequestMapping( value="/eliminarXCodigo", method=RequestMethod.POST)
    public String eliminar( @RequestParam("codigo") String cod) {
        String url = "https://agile-plains-29293.herokuapp.com/api/v1/eliminarProductos/" + cod;
        
        RestTemplate rt =  new RestTemplate();
        rt.delete(url);
        
        // Ir a la pagina index        
        return "redirect:/";
        
    }
    
    
    @RequestMapping( value="/actualizar", method=RequestMethod.POST)
    public String actualizar( 
            @RequestParam("codigo") String cod, 
            @RequestParam("nombre") String nombre, 
            @RequestParam("precio") String precio  ) {
        String url = "https://agile-plains-29293.herokuapp.com/api/v1/actualizarProductos/" + cod;
        
        RestTemplate rt =  new RestTemplate();
        Map<String,String> request = new HashMap<>();
        request.put("codigo", cod);
        request.put("nombre", nombre);
        request.put("precio", precio);
        
        HttpEntity<Map> req = new HttpEntity(request);
        rt.put(url, req);
        // Ir a la pagina index        
        return "redirect:/";
        
    }
    
}
