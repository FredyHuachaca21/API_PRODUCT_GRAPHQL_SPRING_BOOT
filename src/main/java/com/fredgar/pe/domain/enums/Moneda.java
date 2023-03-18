package com.fredgar.pe.domain.enums;

public enum Moneda {
  USD("Dólar estadounidense"),
  EUR("Euro"),
  GBP("Libra esterlina -Reino Unido"),
  JPY("Yen japonés"),
  CHF("Franco suizo"),
  CAD("Dólar canadiense"),
  AUD("Dólar australiano"),
  NZD("Dólar neozelandés"),
  CNY("Yuan chino"),
  HKD("Dólar de Hong Kong"),
  SGD("Dólar de Singapur"),
  MXN("Peso mexicano"),
  BRL("Real brasileño"),
  ARS("Peso argentino"),
  COP("Peso colombiano"),
  CLP("Peso chileno"),
  PEN("Sol peruano");
      
  private final String nombre;

  Moneda(String nombre){
    this.nombre = nombre;
  }
}
