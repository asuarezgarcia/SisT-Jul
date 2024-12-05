package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.app.modelo.Producto;
import uniandes.edu.co.app.repositorio.CategoriaRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private CategoriaRepository categoriaRepositry;

    // Crear un nuevo producto
    @PostMapping("/new/save/{codigo}")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto, @PathVariable("codigo") int codigo) {
        try {
            if (!categoriaRepositry.existsById(codigo)) {
                return new ResponseEntity<>("La categoría no existe", HttpStatus.BAD_REQUEST);
            } else if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
                return new ResponseEntity<>("El nombre del producto es requerido", HttpStatus.BAD_REQUEST);
            } else if (producto.getPrecio_unidad() == 0.0) {
                return new ResponseEntity<>("El precio del producto es requerido", HttpStatus.BAD_REQUEST);
            }
            categoriaRepositry.findById(codigo).get().getProductos().add(producto);
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


        // Obtener un producto por su código
    @GetMapping("/get/{codigo}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable("codigo") String codigo) {
        try {
            // Buscar el producto por su código
            Producto producto = categoriaRepositry.findAll()
                    .stream()
                    .flatMap(categoria -> categoria.getProductos().stream())
                    .filter(p -> p.getCodigo().equals(codigo))
                    .findFirst()
                    .orElse(null);

            if (producto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un producto
    @PostMapping("/edit/save/{codigo}")
    public ResponseEntity<String> actualizarProducto(@RequestBody Producto producto, @PathVariable("codigo") String codigo) {
        try {
            // Buscar el producto
            Producto productoExistente = categoriaRepositry.findAll()
                    .stream()
                    .flatMap(categoria -> categoria.getProductos().stream())
                    .filter(p -> p.getCodigo().equals(codigo))
                    .findFirst()
                    .orElse(null);

            if (productoExistente == null) {
                return new ResponseEntity<>("El producto no existe", HttpStatus.BAD_REQUEST);
            }

            // Actualizar el producto
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio_unidad(producto.getPrecio_unidad());
            productoExistente.setCosto_bodega(producto.getCosto_bodega());
            productoExistente.setPresentacion(producto.getPresentacion());
            productoExistente.setCantidad(producto.getCantidad());
            productoExistente.setUnidad_medida(producto.getUnidad_medida());
            productoExistente.setEspecificacion(producto.getEspecificacion());
            productoExistente.setProveedores(producto.getProveedores());

            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

