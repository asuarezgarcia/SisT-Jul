package uniandes.edu.co.app.modelo;

import java.util.Date;

import lombok.ToString;

@ToString
public class Especificacion {
    private double costo_promedio;
    private int capacidad;
    private int ocupacion_actual;
    private Date expiracion;
    private int minimo;
    private String codigo_producto;

    public Especificacion() {;}

    public Especificacion(double costo_promedio, int capacidad, int ocupacion_actual, Date expiracion, int minimo, String codigo_producto) {
        this.costo_promedio = costo_promedio;
        this.capacidad = capacidad;
        this.ocupacion_actual = ocupacion_actual;
        this.expiracion = expiracion;
        this.minimo = minimo;
        this.codigo_producto = codigo_producto;
    }

    public double getCosto_promedio() {
        return costo_promedio;
    }

    public void setCosto_promedio(double costo_promedio) {
        this.costo_promedio = costo_promedio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getOcupacion_actual() {
        return ocupacion_actual;
    }

    public void setOcupacion_actual(int ocupacion_actual) {
        this.ocupacion_actual = ocupacion_actual;
    }

    public Date getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Date expiracion) {
        this.expiracion = expiracion;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    
}
