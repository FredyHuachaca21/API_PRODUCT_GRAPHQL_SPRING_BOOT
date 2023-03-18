package com.fredgar.pe.domain.model;

import com.fredgar.pe.domain.enums.Moneda;
import com.fredgar.pe.domain.enums.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Producto {
  @Id
  private String id;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Integer stock;
  private UnidadMedida unidadMedida;
  private Moneda moneda;
  private String fechaIngreso;
  private String fechaActualizacion;
  private String fechaVencimiento;
  private String marcaId;
  private String categoriaId;
}
