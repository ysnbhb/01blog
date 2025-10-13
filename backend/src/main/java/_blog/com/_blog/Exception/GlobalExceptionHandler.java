package _blog.com._blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.ServletException;

import org.springframework.web.bind.MissingServletRequestParameterException;
import jakarta.validation.ValidationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.converter.HttpMessageNotReadableException;

//handel global exception 
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
                String message = ex.getBindingResult().getFieldErrors().isEmpty()
                                ? "Validation error"
                                : ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("error", message));
        }

        @ExceptionHandler(ProgramExeption.class)
        public ResponseEntity<Map<String, String>> handleUserException(ProgramExeption ex) {
                return ResponseEntity
                                .status(ex.getStatus())
                                .body(Map.of("error", ex.getMessage()));
        }

        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseEntity<Map<String, String>> handleParamException(MissingServletRequestParameterException ex) {
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("error", "Missing required parameter: " + ex.getParameterName()));
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
                        HttpMessageNotReadableException ex) {
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("error", "Invalid or missing request body"));
        }

        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<Map<String, String>> handleNotFound(NoHandlerFoundException ex) {
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(Map.of("error", "Page not found"));
        }

        @ExceptionHandler(ServletException.class)
        public ResponseEntity<Map<String, String>> handleServletException(ServletException ex) {
                HttpStatus code = HttpStatus.FORBIDDEN;
                return ResponseEntity
                                .status(code)
                                .body(Map.of("error", ex.getMessage()));
        }

        @ExceptionHandler(IOException.class)
        public ResponseEntity<Map<String, String>> handleIOException(IOException ex) {
                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Map.of("error", "File processing failed: " + ex.getMessage()));
        }

        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<Map<String, String>> handleGeneralExceptionValidEntity(ValidationException ex) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "invalid input");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
                ex.printStackTrace();
                Map<String, String> response = new HashMap<>();
                response.put("error", "Unexpected error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}
