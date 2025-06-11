package com.skillnest.everythingsouvneirs.exception;

public class InvalidEmailException extends RuntimeException {
  public InvalidEmailException(String message) {
    super(message);
  }
}
