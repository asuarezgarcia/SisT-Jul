package uniandes.edu.co.app.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.ToString;

@ToString
public class Bodega {

    @Id
    private int id;
    private String nombre;
    private Integer tamanio;
    private List<Especificacion> especificaciones;

    public Bodega() {;}

    public Bodega(int id, String nombre, Integer tamanio, List<Especificacion> especificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.especificaciones = especificaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public List<Especificacion> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(List<Especificacion> especificaciones) {
        this.especificaciones = especificaciones;
    }

    
}