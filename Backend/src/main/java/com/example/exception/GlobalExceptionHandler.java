package com.example.exception;

import com.example.response.ResponseObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ResponseObject<Object>> handleCustomerNotFound(CustomerNotFoundException ex) {

        logger.error("CustomerNotFoundException: {}", ex.getMessage());

        ResponseObject<Object> response = new ResponseObject<>(404, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ResponseObject<Object>> handleAccountNotFound(AccountNotFoundException ex) {

        logger.error("AccountNotFoundException: {}", ex.getMessage());

        ResponseObject<Object> response = new ResponseObject<>(404, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCustomerDataException.class)
    public ResponseEntity<ResponseObject<Object>> handleInvalidCustomerData(InvalidCustomerDataException ex) {

        logger.error("InvalidCustomerDataException: {}", ex.getMessage());

        ResponseObject<Object> response = new ResponseObject<>(400, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccountDataException.class)
    public ResponseEntity<ResponseObject<Object>> handleInvalidAccountData(InvalidAccountDataException ex) {

        logger.error("InvalidAccountDataException: {}", ex.getMessage());

        ResponseObject<Object> response = new ResponseObject<>(400, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject<Object>> handleGenericException(Exception ex) {

        logger.error("Unhandled Exception: {} | Cause: {}", ex.getMessage(), ex.getCause(), ex);

        ResponseObject<Object> response = new ResponseObject<>(500, "Internal Server Error", null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}