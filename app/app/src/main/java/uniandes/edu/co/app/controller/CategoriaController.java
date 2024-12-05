package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.app.modelo.Categoria;
import uniandes.edu.co.app.repositorio.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una nueva categoria de producto
    @PostMapping("/new/save")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria) {
        try {
            if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
                return new ResponseEntity<>("El nombre de la categoría es requerido", HttpStatus.BAD_REQUEST);
            } else if (categoriaRepository.existsById(categoria.getcodigo())) {
                return new ResponseEntity<>("La categoría ya existe", HttpStatus.BAD_REQUEST);
            }
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoría creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una categoria por su ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@RequestBody int id) {
        try {
            if (categoriaRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(categoriaRepository.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
