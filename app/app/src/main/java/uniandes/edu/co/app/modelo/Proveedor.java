package uniandes.edu.co.app.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="proveedores")
@ToString
public class Proveedor {
    @Id
    private String nit;
    private String nombre;
    private String direccion;
    private Long telefono_contacto;
    private String nombre_contacto;

    public Proveedor() {;}

    public Proveedor(String nit, String nombre, String direccion, Long telefono_contacto, String nombre_contacto) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono_contacto = telefono_contacto;
        this.nombre_contacto = nombre_contacto;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(Long telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    
}



