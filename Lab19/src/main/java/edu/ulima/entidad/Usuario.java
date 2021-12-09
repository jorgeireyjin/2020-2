package edu.ulima.entidad;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Entity(name="EUSUARIO")
@Table(name = "TUSUARIO")
public class Usuario implements Serializable {

    @Id
    @Column(name = "USUARIO", length = 20, nullable = false)
    private String usuario;

    @Column(name = "ACTIVO", length = 1, nullable = false)
    private boolean activo;

    @Column(name = "CLAVE", length = 128, nullable = false)
    private String clave;

    @Column(name = "ROL", length = 20, nullable = false)
    private String rol;

}