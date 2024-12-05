package uniandes.edu.co.app.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SucursalRepositoryConsultas {
    private final MongoTemplate mongoTemplate;

    public SucursalRepositoryConsultas(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> obtenerInventarioSucursal(int idSucursal) {
        List<Document> pipeline = List.of(
            new Document("$match", new Document("id", idSucursal)),
            new Document("$unwind", "$bodegas"),
            new Document("$unwind", "$bodegas.especificaciones"),
            new Document("$lookup", new Document("from", "categorias")
                .append("let", new Document("codigo_producto", "$bodegas.especificaciones.codigo_producto"))
                .append("pipeline", List.of(
                    new Document("$unwind", "$productos"),
                    new Document("$match", new Document("$expr", new Document("$eq", List.of("$productos.codigo", "$$codigo_producto")))),
                    new Document("$project", new Document("producto", "$productos")))
                )
                .append("as", "producto_info")),
            new Document("$addFields", new Document("producto", new Document("$arrayElemAt", List.of("$producto_info.producto", 0)))),
            new Document("$limit", 10)
        );
        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new ArrayList<>());
    }
    
    public List<Document> productosFiltroMin(int idSucursal, double minPrecio, double maxPrecio, String categoria, String fechaVencimiento) {
        // Pipeline de agregación
        List<Document> pipeline = List.of(
            new Document("$match", new Document("_id", idSucursal)),
            new Document("$unwind", "$bodegas"),
            new Document("$unwind", "$bodegas.especificaciones"),
            new Document("$lookup", new Document("from", "categorias")
                .append("let", new Document("codigo_producto", "$bodegas.especificaciones.codigo_producto"))
                .append("pipeline", List.of(
                    new Document("$unwind", "$productos"),
                    new Document("$match", new Document("$expr",
                        new Document("$eq", List.of("$productos._id", "$$codigo_producto")))),
                    new Document("$project", new Document("_id", 0)
                        .append("producto", "$productos")
                        .append("nombre_categoria", "$nombre")
                        .append("descripcion_categoria", "$descripcion"))
                ))
                .append("as", "productos_info")),
            new Document("$match", new Document("$and", List.of(
                new Document("productos_info.producto.precio_unidad", 
                    new Document("$gte", minPrecio).append("$lte", maxPrecio)),
                new Document("bodegas.especificaciones.expiracion",
                    new Document("$lte", fechaVencimiento)),
                new Document("productos_info.nombre_categoria", categoria)
            ))),
            new Document("$project", new Document("_id", 0)
                .append("sucursal", "$nombre")
                .append("bodega", "$bodegas.nombre")
                .append("producto", "$productos_info.producto.nombre")
                .append("precio", "$productos_info.producto.precio_unidad")
                .append("categoria", "$productos_info.nombre_categoria")
                .append("descripcion", "$productos_info.descripcion_categoria")
                .append("cantidad_disponible", "$bodegas.especificaciones.ocupacion_actual")
                .append("fecha_vencimiento", "$bodegas.especificaciones.expiracion"))
        );
        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new ArrayList<>());
    }

    public List<Document> productosFiltroMax(int idSucursal, double minPrecio, double maxPrecio, String categoria, String fechaVencimiento) {
        // Pipeline de agregación
        List<Document> pipeline = List.of(
            new Document("$match", new Document("_id", idSucursal)),
            new Document("$unwind", "$bodegas"),
            new Document("$unwind", "$bodegas.especificaciones"),
            new Document("$lookup", new Document("from", "categorias")
                .append("let", new Document("codigo_producto", "$bodegas.especificaciones.codigo_producto"))
                .append("pipeline", List.of(
                    new Document("$unwind", "$productos"),
                    new Document("$match", new Document("$expr",
                        new Document("$eq", List.of("$productos._id", "$$codigo_producto")))),
                    new Document("$project", new Document("_id", 0)
                        .append("producto", "$productos")
                        .append("nombre_categoria", "$nombre")
                        .append("descripcion_categoria", "$descripcion"))
                ))
                .append("as", "productos_info")),
            new Document("$match", new Document("$and", List.of(
                new Document("productos_info.producto.precio_unidad", 
                    new Document("$gte", minPrecio).append("$lte", maxPrecio)),
                new Document("bodegas.especificaciones.expiracion",
                    new Document("$gte", fechaVencimiento)),
                new Document("productos_info.nombre_categoria", categoria)
            ))),
            new Document("$project", new Document("_id", 0)
                .append("sucursal", "$nombre")
                .append("bodega", "$bodegas.nombre")
                .append("producto", "$productos_info.producto.nombre")
                .append("precio", "$productos_info.producto.precio_unidad")
                .append("categoria", "$productos_info.nombre_categoria")
                .append("descripcion", "$productos_info.descripcion_categoria")
                .append("cantidad_disponible", "$bodegas.especificaciones.ocupacion_actual")
                .append("fecha_vencimiento", "$bodegas.especificaciones.expiracion"))
        );
        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new ArrayList<>());
    }
}
