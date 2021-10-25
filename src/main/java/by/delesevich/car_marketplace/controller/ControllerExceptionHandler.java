package by.delesevich.car_marketplace.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String BASE_ENTITY_PACKAGE = "by.delesevich.car_marketplace.entity.";

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    List<String> errors = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    body.put("errors", errors);

    return new ResponseEntity<>(body, headers, status);
  }


  //Handler for Pageable object exceptions
  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  //Handler for Sort object exceptions
  @ExceptionHandler(PropertyReferenceException.class)
  protected ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  //Handler for creating new lot with incorrect seller id or vehicle id
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
    String newMessage = e.getMessage().replaceAll(BASE_ENTITY_PACKAGE, "");
    return new ResponseEntity<>(newMessage, HttpStatus.BAD_REQUEST);
  }

}
