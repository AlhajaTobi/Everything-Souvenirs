package com.skillnest.everythingsouvneirs.exception;

public class OtpExpiredException extends RuntimeException {
  public OtpExpiredException(String message) {
    super(message);
  }
}
