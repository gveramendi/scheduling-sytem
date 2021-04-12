package com.example.schedulingsystem.exception;

public class EntityFormatException extends RuntimeException {

  public EntityFormatException(String entityName) {
    super("Invalid format on entity " +  entityName);
  }

}
