package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.app.modelo.Proveedor;
import uniandes.edu.co.app.repositorio.ProveedorRepository;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear un nuevo proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            if (proveedor.getNit() == null || proveedor.getNit().isEmpty()) {
                return new ResponseEntity<>("El NIT del proveedor es requerido", HttpStatus.BAD_REQUEST);
            } else if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
                return new ResponseEntity<>("El nombre del proveedor es requerido", HttpStatus.BAD_REQUEST);
            } else if (proveedor.getDireccion() == null || proveedor.getDireccion().isEmpty()) {
                return new ResponseEntity<>("La dirección del proveedor es requerida", HttpStatus.BAD_REQUEST);
            } else if (proveedor.getNombre_contacto() == null || proveedor.getNombre_contacto().isEmpty()) {
                return new ResponseEntity<>("El nombre de contacto del proveedor es requerido", HttpStatus.BAD_REQUEST);
            }
            proveedorRepository.save(proveedor);
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un proveedor existente
    @PostMapping("/edit/save")
    public ResponseEntity<String> actualizarProveedor(@RequestBody Proveedor proveedor) {
        try {
            if (proveedor.getNit() == null || proveedor.getNit().isEmpty()) {
                return new ResponseEntity<>("El NIT del proveedor es requerido", HttpStatus.BAD_REQUEST);
            } else if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
                return new ResponseEntity<>("El nombre del proveedor es requerido", HttpStatus.BAD_REQUEST);
            } else if (proveedor.getDireccion() == null || proveedor.getDireccion().isEmpty()) {
                return new ResponseEntity<>("La dirección del proveedor es requerida", HttpStatus.BAD_REQUEST);
            } else if (proveedor.getNombre_contacto() == null || proveedor.getNombre_contacto().isEmpty()) {
                return new ResponseEntity<>("El nombre de contacto del proveedor es requerido", HttpStatus.BAD_REQUEST);
            } else if (proveedorRepository.findById(proveedor.getNit()).isEmpty()) {
                return new ResponseEntity<>("El proveedor no existe", HttpStatus.BAD_REQUEST);
            }
            proveedorRepository.actualizarProveedor(
                proveedor.getNit(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getTelefono_contacto(),
                proveedor.getNombre_contacto()
            );
            return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el proveedor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
