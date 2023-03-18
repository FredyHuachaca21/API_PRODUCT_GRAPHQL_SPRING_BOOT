package com.fredgar.pe.domain.mutation;

import com.fredgar.pe.domain.model.Categoria;
import com.fredgar.pe.domain.repository.CategoriaRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoriaMutationResolver implements GraphQLMutationResolver {

  private final CategoriaRepository repository;

  public Categoria crearCategoria(Categoria categoria){
    return repository.save(categoria).block();
  }

  public Categoria actualizarCategoria(String id, Categoria categoria) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Categoría no encontrada")))
        .map(existingCategoria -> {
          // Actualiza los campos de la categoría existente con los valores de la categoría proporcionada
          existingCategoria.setNombre(categoria.getNombre());
          existingCategoria.setDescripcion(categoria.getDescripcion());
          existingCategoria.setFechaCreacion(categoria.getFechaCreacion());
          existingCategoria.setFechaActualizacion(categoria.getFechaActualizacion());
          return existingCategoria;
        })
        .flatMap(repository::save)
        .block();
  }

  public Boolean eliminarCategoria(String id) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Categoría no encontrada")))
        .flatMap(existingCategoria -> repository.delete(existingCategoria)
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
