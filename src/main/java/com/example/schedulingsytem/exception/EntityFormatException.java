package com.example.schedulingsytem.exception;

public class EntityFormatException extends RuntimeException {

  public EntityFormatException(String entityName) {
    super("Invalid format on entity " +  entityName);
  }

}
