package com.example.schedulingsytem.exception;

public class ServiceException extends RuntimeException {

  public ServiceException(String serviceMessageException) {
    super("Service exception: " +  serviceMessageException);
  }

}
