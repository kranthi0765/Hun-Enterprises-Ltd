package com.hub_entrprice.ltd.Exceptions;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.hub_entrprice.ltd.apiResponse.ApiResponse;
import com.hub_entrprice.ltd.constants.GrnericConstant;

@RestControllerAdvice
public class GlobelExceptionalHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorsMap.put(error.getField(), error.getDefaultMessage());
        });
        ApiResponse<Map<String, String>> response = new ApiResponse<>(false, GrnericConstant.VALIDATION_FAILED_MESSAGE, errorsMap);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>(false, GrnericConstant.UNABLE_TO_PROCESS_REQUEST_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
