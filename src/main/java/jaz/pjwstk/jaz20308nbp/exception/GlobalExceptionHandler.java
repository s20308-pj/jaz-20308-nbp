package jaz.pjwstk.jaz20308nbp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> notFoundHandle(HttpClientErrorException ex) {
        String bodyOfResponse = ex.getResponseBodyAsString();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> badRequestHandler(HttpClientErrorException ex) {
        String bodyOfResponse = ex.getResponseBodyAsString();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> internalServerErrorHandler(HttpServerErrorException ex) {
        String bodyOfResponse = ex.getResponseBodyAsString();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyOfResponse);
    }

    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT, reason = "NBP service not responding")
    @ExceptionHandler(ConnectException.class)
    public void handleConnEx(ConnectException ex) {
    }
}