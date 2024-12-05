package uniandes.edu.co.app.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.app.modelo.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
        // Actualizar un proveedor por su ID
        @Query("{ nit: ?0 }")
        @Update("{ $set: { nombre: ?1, direccion: ?2, telefono_contacto: ?3, nombre_contacto: ?4 }}")
        void actualizarProveedor(String nit, String nombre, String direccion, Long telefono_contacto, String nombre_contacto);

}

