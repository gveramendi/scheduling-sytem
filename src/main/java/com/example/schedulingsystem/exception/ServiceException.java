package com.example.schedulingsystem.exception;

public class ServiceException extends RuntimeException {

  public ServiceException(String serviceMessageException) {
    super("Service exception: " +  serviceMessageException);
  }

}
