package uniandes.edu.co.app.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.app.modelo.Sucursal;
import uniandes.edu.co.app.repositorio.SucursalRepository;
import uniandes.edu.co.app.repositorio.SucursalRepositoryConsultas;


@RestController
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalRepositoryConsultas sucursalRepositoryConsultas;
    
     // Crear una nueva sucursal
    @PostMapping("/new/save")
    public ResponseEntity<String> crearSucursal(@RequestBody Sucursal sucursal) {
        try {
            if (sucursalRepository.existsById(sucursal.getId())) {
                return new ResponseEntity<>("La sucursal ya existe", HttpStatus.BAD_REQUEST);
            } else if (sucursal.getNombre() == null || sucursal.getNombre().isEmpty()) {
                return new ResponseEntity<>("El nombre de la sucursal es requerido", HttpStatus.BAD_REQUEST);
            } else if (sucursal.getDireccion() == null || sucursal.getDireccion().isEmpty()) {
                return new ResponseEntity<>("La dirección de la sucursal es requerida", HttpStatus.BAD_REQUEST);
            }
            sucursalRepository.save(sucursal);
            return new ResponseEntity<>("Sucursal creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
    
    // Obtener el inventario de una sucursal
    @GetMapping("/inventario")
    public ResponseEntity<List<Document>> obtenerInventarioSucursal(@RequestParam int sucursal_id) {
        try {
            if (sucursalRepository.findById(sucursal_id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Document> inventario = sucursalRepositoryConsultas.obtenerInventarioSucursal(sucursal_id);
            return new ResponseEntity<>(inventario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener productos de una sucursal con filtro mínimo
    @GetMapping("/productos/filtro/min")
    public ResponseEntity<List<Document>> productosFiltroMin(@RequestParam int id,
                                                        @RequestParam double minPrecio, 
                                                        @RequestParam double maxPrecio, 
                                                        @RequestParam String categoria, 
                                                        @RequestParam String fechaVencimiento) {
        try {
            if (sucursalRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Document> productos = sucursalRepositoryConsultas.productosFiltroMin(id, minPrecio, maxPrecio, categoria, fechaVencimiento);
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener productos de una sucursal con filtro máximo
    @GetMapping("/productos/filtro/max")
    public ResponseEntity<List<Document>> productosFiltroMax(@RequestParam int id, 
                                                        @RequestParam double minPrecio, 
                                                        @RequestParam double maxPrecio, 
                                                        @RequestParam String categoria, 
                                                        @RequestParam String fechaVencimiento) {
        try {
            if (sucursalRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Document> productos = sucursalRepositoryConsultas.productosFiltroMax(id, minPrecio, maxPrecio, categoria, fechaVencimiento);
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}