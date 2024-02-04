package com.nelumbo.parqueadero.controllers.common;

import com.nelumbo.parqueadero.services.common.ParqueaderoException;
import com.nelumbo.parqueadero.services.common.RegistroException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(RegistroException.class)
    public ResponseEntity<ErrorInfo> registroException(HttpServletRequest request, RegistroException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> registroException(HttpServletRequest request, MethodArgumentNotValidException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), "Datos de entrada invalidos", request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParqueaderoException.class)
    public ResponseEntity<ErrorInfo> registroException(HttpServletRequest request, ParqueaderoException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }



}
