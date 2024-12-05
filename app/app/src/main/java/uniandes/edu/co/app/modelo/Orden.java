package uniandes.edu.co.app.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;


import lombok.ToString;

@ToString
public class Orden {
    @Id
    private int id;
    private String fecha_entrega;
    private String estado;
    private String nit_proveedor;
    private List<ProductoOrden> productos_orden;

    public Orden() {;}

    public Orden(int id, String fecha_entrega, String estado, String nit_proveedor, List<ProductoOrden> productos_orden) {
        this.id = id;
        this.fecha_entrega = fecha_entrega;
        this.estado = estado;
        this.nit_proveedor = nit_proveedor;
        this.productos_orden = productos_orden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    public List<ProductoOrden> getProductos_orden() {
        return productos_orden;
    }

    public void setProductos_orden(List<ProductoOrden> productos_orden) {
        this.productos_orden = productos_orden;
    }

    
}

