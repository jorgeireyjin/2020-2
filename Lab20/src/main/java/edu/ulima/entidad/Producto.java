package edu.ulima.entidad;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@NoArgsConstructor
@Entity(name="EPRODUCTO")
@Table(name = "TPRODUCTO")
public class Producto implements Serializable {

    @Id
    @Column(name = "CODIGO", length = 20, nullable = false)
    private String codigo;

    @Column(name = "NOMBRE", length = 255, nullable = false)
    private String nombre;

    @Column(name = "PRECIO", nullable = false)
    private double precio;

    @Lob
    @Column(name = "IMAGEN", length = Integer.MAX_VALUE, nullable = true)
    private byte[] imagen;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_VIG", nullable = false)
    private Date fecha_vigencia;

    public Producto(String codigo, String nombre, double precio, Date fecha_vigencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha_vigencia = fecha_vigencia;

    }

}