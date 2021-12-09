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
    private ProductoRepositorio repo;
    
    @Override
    public boolean supports(Class<?> clase) {
        return clase == ProductoForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {

        System.out.println("===< en el Validador ! ");
        
        ProductoForm pf = (ProductoForm)o;
        
        // Validar
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "productoForm.codigo.Vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "productoForm.nombre.Vacio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "productoForm.precio.Vacio");
        
        String codigo = pf.getCodigo();
        
        // Validar codigo que cumpla un patro o que ya exista
        if ( codigo != null && codigo.length() > 0 ) {
            // Expresiones regulares 
            if ( codigo.matches("\\s+")) {
                errors.rejectValue("codigo", "productoForm.codigo.Patron");
            } else if ( pf.isNewProduct() ){
                Producto p = repo.findByCodigo(codigo);
                if ( p != null ) {
                    errors.rejectValue("codigo", "productoForm.codigo.Duplicado");                    
                }
            }
        }
        
    }

}
