package com.orientapsi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice funciona igual en WebFlux que en MVC: intercepta la
 * excepción que viaja "dentro" del Mono cuando este llega a error (Mono.error(...))
 * y la traduce a una respuesta HTTP, sin que el controller tenga que hacer
 * try/catch en ningún lado.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ModelNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
