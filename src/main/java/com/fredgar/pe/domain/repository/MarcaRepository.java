package com.fredgar.pe.domain.repository;

import com.fredgar.pe.domain.model.Marca;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MarcaRepository extends ReactiveMongoRepository<Marca, String> {
}
