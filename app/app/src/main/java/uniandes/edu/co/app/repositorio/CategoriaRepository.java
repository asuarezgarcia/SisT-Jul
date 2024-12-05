package uniandes.edu.co.app.repositorio;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.app.modelo.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {
    @Aggregation("{$unwind: '$productos'}, {$match: {'productos.codigo': ?0}}")
    Categoria findProductoByCodigo(String codigo);
}