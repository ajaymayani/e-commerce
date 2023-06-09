package com.example.ecommerce.exceptions;


import com.example.ecommerce.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setMessages(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//
//        Map<String, String> errors = new HashMap<>();
//
//        for (ObjectError error : e.getBindingResult().getAllErrors()) {
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        }
//
//        ApiValidationResponse response = new ApiValidationResponse();
//        response.setSuccess(false);
//        response.setErrors(errors);
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}