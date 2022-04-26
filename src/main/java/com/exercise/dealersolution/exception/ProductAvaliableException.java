package com.exercise.dealersolution.exception;

public class ProductAvaliableException extends RuntimeException{

  public ProductAvaliableException() {
    super("Produto encontrado com estoque!");
  }
}
