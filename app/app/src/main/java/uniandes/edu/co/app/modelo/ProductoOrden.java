package uniandes.edu.co.app.modelo;

import lombok.ToString;

@ToString
public class ProductoOrden {
    private int cantidad_pedida;
    private int cantidad_recibida;
    private double precio_acordado;
    private double costo_promedio;
    private String codigo_producto;

    public ProductoOrden() {;}

    public ProductoOrden(int cantidad_pedida, int cantidad_recibida, double precio_acordado, double costo_promedio, String codigo_producto) {
        this.cantidad_pedida = cantidad_pedida;
        this.cantidad_recibida = cantidad_recibida;
        this.precio_acordado = precio_acordado;
        this.costo_promedio = costo_promedio;
        this.codigo_producto = codigo_producto;
    }

    public int getCantidad_pedida() {
        return cantidad_pedida;
    }

    public void setCantidad_pedida(int cantidad_pedida) {
        this.cantidad_pedida = cantidad_pedida;
    }

    public int getCantidad_recibida() {
        return cantidad_recibida;
    }

    public void setCantidad_recibida(int cantidad_recibida) {
        this.cantidad_recibida = cantidad_recibida;
    }

    public double getPrecio_acordado() {
        return precio_acordado;
    }

    public void setPrecio_acordado(double precio_acordado) {
        this.precio_acordado = precio_acordado;
    }

    public double getCosto_promedio() {
        return costo_promedio;
    }

    public void setCosto_promedio(double costo_promedio) {
        this.costo_promedio = costo_promedio;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    
}
