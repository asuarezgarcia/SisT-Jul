package uniandes.edu.co.app.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.app.modelo.Sucursal;

public interface SucursalRepository extends MongoRepository<Sucursal, Integer> {
    
}