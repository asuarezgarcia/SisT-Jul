package uniandes.edu.co.app.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.app.modelo.Bodega;


public interface BodegaRepository extends MongoRepository<Bodega, Integer> {

   // Eliminar una bodega por su ID
   @Query(value = "{_id: ?0}", delete = true)
   void eliminarBodegaPorId(ObjectId id);
}