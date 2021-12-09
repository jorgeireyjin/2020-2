package edu.ulima.modelo;

import lombok.Data;

@Data
public class ProductoInfo {
    private String codigo;
    private String nombre;
    private double precio;
    
    public ProductoInfo( String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
}
