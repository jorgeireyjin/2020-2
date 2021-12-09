package edu.ulima.entidad;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "productos")
public class Producto implements Serializable {

    @Id
    @Indexed(unique=true)
    private String codigo;

    private String nombre;

    private double precio;

    private byte[] imagen;
    
    private Date fecha_vigencia;

    public Producto(String codigo, String nombre, double precio, Date fecha_vigencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha_vigencia = fecha_vigencia;

    }

}