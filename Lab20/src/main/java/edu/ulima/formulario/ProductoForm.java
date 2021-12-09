package edu.ulima.formulario;

import edu.ulima.entidad.Producto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductoForm {
    private String codigo;
    private String nombre;
    private double precio;

    private boolean newProduct = false;

    // Upload de archivo
    private MultipartFile fileData;

    public ProductoForm() {
        this.newProduct= true;
    }

    public ProductoForm(Producto p) {
        this.codigo = p.getCodigo();
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
    }


}