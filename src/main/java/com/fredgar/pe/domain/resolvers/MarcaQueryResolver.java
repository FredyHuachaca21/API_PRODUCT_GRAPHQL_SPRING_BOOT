package com.fredgar.pe.domain.resolvers;

import com.fredgar.pe.domain.model.Marca;
import com.fredgar.pe.domain.repository.MarcaRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MarcaQueryResolver implements GraphQLQueryResolver {

  private final MarcaRepository repository;

  public Marca buscarMarcaPorId(String id) {
    return repository.findById(id).block();
  }

  public List<Marca> buscarTodasLasMarcas() {
    return repository.findAll().collectList().block();
  }
}
