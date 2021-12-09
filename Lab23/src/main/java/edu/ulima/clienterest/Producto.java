
package edu.ulima.clienterest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true)
/*
http://spring.io/guides/gs/consuming-rest
*/
public class Producto {

        private String codigo;
        private String nombre;
        private float precio;
        
}
