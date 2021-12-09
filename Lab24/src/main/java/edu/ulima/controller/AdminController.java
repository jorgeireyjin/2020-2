package edu.ulima.controller;

import edu.ulima.entidad.Producto;
import edu.ulima.formulario.ProductoForm;
import edu.ulima.persistencia.ProductoRepositorio;
import edu.ulima.validator.ProductoFormValidator;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@Transactional
public class AdminController {
    
    @Autowired
    private ProductoRepositorio repo;
    
    @Autowired
    private ProductoFormValidator pfv;
    
    @InitBinder
    public void myInitBind(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if ( target == null) {
            return;
        }
        
        if ( target.getClass() == ProductoForm.class) {
            dataBinder.setValidator(pfv);
        }
        
    }
    
    
   // GET: Show Login Page
   @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
   public String login(Model model) {
      return "login";
   }

   @RequestMapping(value = { "/admin/usuarioInfo" }, method = RequestMethod.GET)
   public String accountInfo(Model model) {

      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      model.addAttribute("userDetails", userDetails);
      return "usuarioInfo";
   }
   
   @RequestMapping(value = { "/admin/manejarProducto" }, method = RequestMethod.GET)   
   public String producto(Model model,
           @RequestParam(value="code", defaultValue="") String code) {
       
       ProductoForm pf = null;
       
       if ( code != null  && code.length() > 0) {
           Producto p = repo.findByCodigo(code);
           if ( p!= null ) {
               pf = new ProductoForm(p);
           }
       } else {
           pf = new ProductoForm();
       }
       
       model.addAttribute("productoForm", pf);
       
       return "producto";
   }

   @RequestMapping(value = { "/admin/manejarProducto" }, method = RequestMethod.POST)   
   public String grabarProducto(Model model,
           @ModelAttribute("productoForm") @Validated ProductoForm productoForm,
           BindingResult result ) {
 
        try {
            
            if ( result.hasErrors() ) {
                return "producto";
            }
            
            String code = productoForm.getCodigo();
            Producto p = null;
            
            boolean isNew = false;
            if ( code != null) {
                p = repo.findByCodigo(code);
            }
            
            if ( p == null ) {
                isNew = true;
                p = new Producto();
                p.setFecha_vigencia( new Date() );
                
            }
            
            p.setCodigo(code);
            p.setNombre( productoForm.getNombre() );
            p.setPrecio( productoForm.getPrecio() );
            p.setImagen( productoForm.getFileData().getBytes() );
            p.setFecha_vigencia( new Date() );
            
            repo.save(p);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return "redirect:/listarProductos";
    }
}