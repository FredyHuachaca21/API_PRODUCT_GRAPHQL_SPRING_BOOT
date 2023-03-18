package com.fredgar.pe.domain.mutation;

import com.fredgar.pe.domain.model.Producto;
import com.fredgar.pe.domain.repository.ProductoRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoMutationResolver implements GraphQLMutationResolver {

  private final ProductoRepository repository;

  public Producto crearProducto(Producto producto){
    return repository.save(producto).block();
  }

  public Producto actualizarProducto(String id, Producto producto) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
        .map(existingProducto -> {
          // Actualiza los campos de la categoría existente con los valores de la categoría proporcionada
          existingProducto.setNombre(producto.getNombre());
          existingProducto.setDescripcion(producto.getDescripcion());
          existingProducto.setPrecio(producto.getPrecio());
          existingProducto.setStock(producto.getStock());
          existingProducto.setUnidadMedida(producto.getUnidadMedida());
          existingProducto.setMoneda(producto.getMoneda());
          existingProducto.setFechaIngreso(producto.getFechaIngreso());
          existingProducto.setFechaActualizacion(producto.getFechaActualizacion());
          existingProducto.setFechaVencimiento(producto.getFechaVencimiento());
          existingProducto.setMarcaId(producto.getMarcaId());
          existingProducto.setCategoriaId(producto.getCategoriaId());
          return existingProducto;
        })
        .flatMap(repository::save).block();
  }

  public Boolean eliminarProducto(String id) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
        .flatMap(existingProducto -> repository.delete(existingProducto)
            .then(Mono.just(true)))
        .onErrorResume(e -> {
          if (e instanceof RuntimeException) {
            return Mono.just(false);
          } else {
            return Mono.error(e);
          }
        }).block();
  }
}
