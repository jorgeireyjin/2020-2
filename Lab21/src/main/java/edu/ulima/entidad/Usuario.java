package edu.ulima.entidad;

import lombok.Data;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor 

@Document(collection = "usuarios")
public class Usuario implements Serializable {

    @Id
    @Indexed(unique=true)
    private String usuario;

    private boolean activo;

    private String clave;

    private String rol;

}