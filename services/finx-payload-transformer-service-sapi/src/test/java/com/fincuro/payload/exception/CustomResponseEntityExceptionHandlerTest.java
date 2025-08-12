package com.fincuro.payload.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomResponseEntityExceptionHandlerTest {

    WebRequest request;
    CustomResponseEntityExceptionHandler customResponseEntityExceptionHandler;
    HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    void setUp() {
        new HttpHeaders();
        customResponseEntityExceptionHandler = new CustomResponseEntityExceptionHandler();
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        request = new ServletWebRequest(servletRequest);
    }

    @Test
    void testHandleAllException() {
        Exception ex = new Exception("Something went wrong");
        ErrorResponse expectedErrorResponse = new ErrorResponse(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        ResponseEntity<ErrorResponse> responseEntity = customResponseEntityExceptionHandler.handleAllException(ex, request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testHandleDataNotFoundException() {
        DataNotFoundException ex = new DataNotFoundException("Data not found");
        ErrorResponse expectedErrorResponse = new ErrorResponse(LocalDateTime.now().toString(), 404, ex.getMessage());
        ResponseEntity<ErrorResponse> responseEntity = customResponseEntityExceptionHandler.handleDataNotFoundException(ex, request);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testHandleDatabaseException() {
        DataBaseException ex = new DataBaseException("Database error");
        ErrorResponse expectedErrorResponse = new ErrorResponse(LocalDateTime.now().toString(), 1011, ex.getMessage());
        ResponseEntity<ErrorResponse> responseEntity = customResponseEntityExceptionHandler.handleDatabaseException(ex, request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void handleMethodArgumentNotValid_BadRequestException(){
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, createBindingResult());
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        WebRequest request = null;
        ResponseEntity<Object> result = customResponseEntityExceptionHandler.handleMethodArgumentNotValid(ex, headers, status, request);
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertTrue(result.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getCode());
        assertEquals("error message", errorResponse.getMessage());
    }

    private BindingResult createBindingResult() {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(new Object(), "objectName");
        result.addError(new FieldError("objectName", "fieldName", "error message"));
        return result;
    }

}
