package com.yuvi.usm.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

  @ControllerAdvice
  public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
      String errorDetails = ex.getMessage();
      return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
      Map<String, Object> errorDetails = new HashMap<>();
      errorDetails.put("message", "An error occurred");
      errorDetails.put("timestamp", new Date());
      errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
