package edu.ulima.validator;

import edu.ulima.entidad.Producto;
import edu.ulima.formulario.ProductoForm;
import edu.ulima.persistencia.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductoFormValidator implements Validator {

   @Autowired
   private ProductoRepositorio productoRepo;

   // This validator only checks for the ProductForm.
   @Override
   public boolean supports(Class<?> clase) {
      return clase == ProductoForm.class;
   }
   

   @Override
   public void validate(Object target, Errors errors) {
       System.out.println("En la ProductoFormValidator ... ");
       
      ProductoForm productoForm = (ProductoForm) target;

      // Ejecutar las validaciones
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "productoForm.codigo.Vacio");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "productoForm.nombre.Vacio");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "productoForm.precio.Vacio");

      String code = productoForm.getCodigo();
      
      if (code != null && code.length() > 0) {
         if (code.matches("\\s+")) {
            errors.rejectValue("codigo", "productoForm.codigo.Patron");
         } else if ( productoForm.isNewProduct() ) {
            Producto p = productoRepo.findByCodigo(code);
            if (p != null) {
               errors.rejectValue("codigo", "productoForm.codigo.Duplicado");
            }
         }
      }
   }

}