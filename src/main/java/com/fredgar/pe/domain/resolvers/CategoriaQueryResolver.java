package com.fredgar.pe.domain.resolvers;

import com.fredgar.pe.domain.model.Categoria;
import com.fredgar.pe.domain.repository.CategoriaRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriaQueryResolver implements GraphQLQueryResolver {

  private final CategoriaRepository repository;

  public Categoria buscarCategoriaPorId(String id) {
    return repository.findById(id).block();
  }

  public List<Categoria> buscarTodasLasCategorias() {
    return repository.findAll().collectList().block();
  }
}
