package edu.ulima.controller;

import edu.ulima.entidad.Producto;
import edu.ulima.formulario.ProductoForm;
import edu.ulima.persistencia.ProductoRepositorio;
import java.util.Date;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
//@Transactional
public class AdminController {
    @Autowired
    ProductoRepositorio productoRepo;

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

   
   //----------------------------------------------------------
   //---- Inicio del Lab18
   //----------------------------------------------------------   
     /*
   Editar producto
   GET: Recupera y muestra informacion del producto
   */
   @RequestMapping(value = { "/admin/manejarProducto" }, method = RequestMethod.GET)
   public String product(Model model, 
           @RequestParam(value = "code", defaultValue = "") String code) {
       
      ProductoForm productoForm = null;

      if (code != null && code.length() > 0) {
         Producto p = productoRepo.findByCodigo(code);
         if (p != null) {
            productoForm = new ProductoForm(p);
         }
      } else {    // Agregado porque salio un error de BInding en el template "producto"
            productoForm = new ProductoForm();         
      }
     
      model.addAttribute("productoForm", productoForm);
      return "producto";
   }

  
    /*
   Editar producto
   POST: Graba las modificaciones al producto
     */
    @RequestMapping(value = {"/admin/manejarProducto"}, method = RequestMethod.POST)
    public String productSave(Model model, //@Validated
            @ModelAttribute("productoForm")  ProductoForm productoForm,
            BindingResult result
    //        final RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            return "producto";
        }

        try {
            String code = productoForm.getCodigo();

            Producto p = null;

            boolean isNew = false;
            if (code != null) {
                p = productoRepo.findByCodigo(code);
            }

            if (p == null) {
                isNew = true;
                p = new Producto();
                p.setFecha_vigencia(new Date());
            }

            p.setCodigo(code);
            p.setNombre(productoForm.getNombre());
            p.setPrecio(productoForm.getPrecio());
            p.setImagen(productoForm.getFileData().getBytes());
            p.setFecha_vigencia(new Date());
            System.out.println("*******************************");
            System.out.println(p);
            System.out.println("*******************************");
            productoRepo.save(p);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            // Show product form.
            return "producto";
        }

        return "redirect:/listarProductos";
    }   
}