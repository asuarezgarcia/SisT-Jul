package uniandes.edu.co.app.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.ToString;
import uniandes.edu.co.app.repositorio.CategoriaRepository;

@ToString
public class Producto {
    @Id
    private String codigo;
    private String nombre;
    private double costo_bodega;
    private double precio_unidad;
    private String presentacion;
    private int cantidad;
    private String unidad_medida;
    private String especificacion;
    private List<String> proveedores;

    public Producto() {;}   
    public Producto(String codigo, String nombre, double costo_bodega, double precio_unidad, String presentacion, int cantidad, String unidad_medida, String especificacion, List<String> proveedores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo_bodega = costo_bodega;
        this.precio_unidad = precio_unidad;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
        this.unidad_medida = unidad_medida;
        this.especificacion = especificacion;
        this.proveedores = proveedores;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto_bodega() {
        return costo_bodega;
    }

    public void setCosto_bodega(double costo_bodega) {
        this.costo_bodega = costo_bodega;
    }

    public double getPrecio_unidad() {
        return precio_unidad;
    }

    public void setPrecio_unidad(double precio_unidad) {
        this.precio_unidad = precio_unidad;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public List<String> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<String> proveedores) {
        this.proveedores = proveedores;
    }



    

}


