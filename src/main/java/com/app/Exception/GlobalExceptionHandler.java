package com.app.Exception;

import com.app.payload.response.ResponseAPIGlobal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseAPIGlobal> handleException(MethodArgumentNotValidException ex)
    {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(
                (error) ->{
                    String filedName = ((FieldError) error).getField();
                    errorMessage.append(filedName + ": "+error.getDefaultMessage() +" \n ");
                }
        );
        ResponseAPIGlobal responseEx = new ResponseAPIGlobal();
        responseEx.setStatus(HttpStatus.BAD_REQUEST.value());
        responseEx.setMessage("");
        responseEx.setError(errorMessage.toString());
        responseEx.setData(null);
        return new ResponseEntity<>(responseEx,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseAPIGlobal> handleException(Exception ex)
    {
        ResponseAPIGlobal responseEx = new ResponseAPIGlobal();
        responseEx.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseEx.setMessage("");
        responseEx.setError(ex.getMessage());
        responseEx.setData(null);
        return new ResponseEntity<>(responseEx,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
