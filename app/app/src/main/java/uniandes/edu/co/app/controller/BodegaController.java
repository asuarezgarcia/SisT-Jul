package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.app.modelo.Bodega;
import uniandes.edu.co.app.repositorio.BodegaRepository;
import uniandes.edu.co.app.repositorio.SucursalRepository;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    // Crear una nueva bodega
    @PostMapping("/new/save/{id}")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega, @PathVariable("id") int id) {
        try {
            if (bodegaRepository.existsById(bodega.getId())) {
                return new ResponseEntity<>("La bodega ya existe", HttpStatus.BAD_REQUEST);
            } else if (bodega.getNombre() == null || bodega.getNombre().isEmpty()) {
                return new ResponseEntity<>("El nombre de la bodega es requerido", HttpStatus.BAD_REQUEST);
            } else if (sucursalRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>("La sucursal no existe", HttpStatus.BAD_REQUEST);
            }
            sucursalRepository.findById(id).get().getBodegas().add(bodega);
            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // Eliminar una bodega por su ID
    @DeleteMapping("/{idSucursal}/{idBodega}/delete")
    public ResponseEntity<String> eliminarBodega(@PathVariable("idSucursal") int idSucursal, @PathVariable("idBodega") int idBodega) {
        try {
            if (sucursalRepository.findById(idSucursal).isEmpty()) {
                return new ResponseEntity<>("La sucursal no existe", HttpStatus.BAD_REQUEST);
            } else if (bodegaRepository.findById(idBodega).isEmpty()) {
                return new ResponseEntity<>("La bodega no existe", HttpStatus.BAD_REQUEST);
            } else if (!sucursalRepository.findById(idSucursal).get().getBodegas().contains(bodegaRepository.findById(idBodega).get())) {
                return new ResponseEntity<>("La bodega no pertenece a la sucursal", HttpStatus.BAD_REQUEST);
            }
            sucursalRepository.findById(idSucursal).get().getBodegas().remove(bodegaRepository.findById(idBodega).get());
            bodegaRepository.deleteById(idBodega);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}