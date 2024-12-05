package uniandes.edu.co.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.app.modelo.Orden;
import uniandes.edu.co.app.modelo.Sucursal;
import uniandes.edu.co.app.repositorio.SucursalRepository;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    @Autowired
    private SucursalRepository sucursalRepositry;

    // Crear una nueva orden de compra
    @PostMapping("/new/save/{id}")
    public ResponseEntity<String> crearOrden(@RequestBody Orden orden, @PathVariable("id") int id) {
        try {
            if (sucursalRepositry.findById(id).isEmpty()) {
                return new ResponseEntity<>("La sucursal no existe", HttpStatus.BAD_REQUEST);
            } else if (orden.getFecha_entrega() == null) {
                return new ResponseEntity<>("La fecha de la orden es requerida", HttpStatus.BAD_REQUEST);
            } else if (orden.getNit_proveedor() == null || orden.getNit_proveedor().isEmpty()) {
                return new ResponseEntity<>("El proveedor de la orden es requerido", HttpStatus.BAD_REQUEST);
            } else if (orden.getProductos_orden().isEmpty()) {
                return new ResponseEntity<>("La orden debe tener al menos un producto", HttpStatus.BAD_REQUEST);
            }
            orden.setEstado("VIGENTE");
            sucursalRepositry.findById(id).get().getOrdenes().add(orden);
            
            return new ResponseEntity<>("Orden de compra creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // Obtener una orden de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<List<Orden>> obtenerOrdenPorId(@PathVariable("id") int id) {
        try {
            List<Sucursal> sucursales = sucursalRepositry.findAll();
            for (Sucursal sucursal : sucursales) {
                for (Orden orden : sucursal.getOrdenes()) {
                    if (orden.getId() == id) {
                        return ResponseEntity.ok(List.of(orden));
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
