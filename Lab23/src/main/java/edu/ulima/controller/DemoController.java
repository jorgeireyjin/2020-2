
package edu.ulima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import edu.ulima.clienterest.Producto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
   public String home() {
      return "index";
   } 

   @RequestMapping(value = {"/consulta"}, method = RequestMethod.GET)
   public String consulta() {
      return "consulta.html";
   } 

  @RequestMapping(value = "/consultarXCodigo",  method = RequestMethod.POST)
  @ResponseBody
   public String consultar( @RequestParam("codigo") String cod) {
      RestTemplate rt = new RestTemplate();
      String url = "http://localhost:8080/api/v1/listarProductos/" + cod;
      
      // Obtener un producto
      Producto p = rt.getForObject(url, Producto.class);
      return  p.toString();
   }
   
  @RequestMapping(value = "/consultarTodos",  method = RequestMethod.POST)
  @ResponseBody
   public ResponseEntity<Producto[]>  consultarTodos() {
      
      RestTemplate rt = new RestTemplate();
      String url = "http://localhost:8080/api/v1/listarProductos";
      
      Producto[] lp = rt.getForObject(url, Producto[].class);
      
      for (Producto p: lp) {
          System.out.println(p);
      }
      
      return ResponseEntity.ok().body(lp);
   }
   
  @RequestMapping(value = "/eliminarXCodigo",  method = RequestMethod.POST)
   public String eliminar( @RequestParam("codigo") String cod) {
      RestTemplate rt = new RestTemplate();
      String url = "http://localhost:8080/api/v1/eliminarProducto/" + cod;
      
      // Eliminar
      rt.delete(url);
      return "redirect:/";
   }
   
@RequestMapping(value = "/actualizar",  method = RequestMethod.POST)
   public String actualizar( @RequestParam("codigo") String cod, 
                                      @RequestParam("nombre") String nombre, 
                                      @RequestParam("precio") String precio ) {
      
       RestTemplate rt = new RestTemplate();
      String url = "http://localhost:8080/api/v1/actualizarProducto/" + cod;
      
      Map<String,String> m = new HashMap<>();
      m.put("codigo", cod);
      m.put("nombre", nombre);
      m.put("precio", precio);
      
      HttpEntity<Map>  request = new HttpEntity(m);
      
      // Actualizar
      rt.put(url, request);
      return "redirect:/";
     
   }

   
}
