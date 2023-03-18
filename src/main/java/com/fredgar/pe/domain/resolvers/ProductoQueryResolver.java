package com.fredgar.pe.domain.resolvers;

import com.fredgar.pe.domain.model.Producto;
import com.fredgar.pe.domain.repository.ProductoRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductoQueryResolver implements GraphQLQueryResolver {

  private final ProductoRepository repository;

  public Producto buscarProductoPorId(String id) {
    return repository.findById(id).block();
  }

  public List<Producto> buscarTodosLosProductos() {
    return repository.findAll().collectList().block();
  }
}
