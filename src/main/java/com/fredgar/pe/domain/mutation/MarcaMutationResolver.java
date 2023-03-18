package com.fredgar.pe.domain.mutation;

import com.fredgar.pe.domain.model.Marca;
import com.fredgar.pe.domain.repository.MarcaRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MarcaMutationResolver implements GraphQLMutationResolver {

  private final MarcaRepository repository;

  public Marca crearMarca(Marca marca){
    return repository.save(marca).block();
  }

  public Marca actualizarMarca(String id, Marca marca) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Marca no encontrada")))
        .map(existingMarca -> {
          // Actualiza los campos de la categoría existente con los valores de la categoría proporcionada
          existingMarca.setNombre(marca.getNombre());
          existingMarca.setDescripcion(marca.getDescripcion());
          existingMarca.setFechaCreacion(marca.getFechaCreacion());
          existingMarca.setFechaActualizacion(marca.getFechaActualizacion());
          return existingMarca;
        })
        .flatMap(repository::save).block();
  }

  public Boolean eliminarMarca(String id) {
    return repository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Marca no encontrada")))
        .flatMap(existingMarca -> repository.delete(existingMarca)
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
