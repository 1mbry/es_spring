package it.syncroweb.es_03_spring_swagger_database.config;

import feign.FeignException;
import it.syncroweb.es_03_spring_swagger_database.dto.ErrorCode;
import it.syncroweb.es_03_spring_swagger_database.dto.ErrorResponse;
import it.syncroweb.es_03_spring_swagger_database.exception.UnprocessableEntityException;
import it.syncroweb.es_03_spring_swagger_database.utils.FormatLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@RestControllerAdvice
public class ServiceExceptionHandler {
    private static final FormatLogger logger = new FormatLogger(LogManager.getLogger(ServiceExceptionHandler.class));


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> noHandlerFoundException(Exception ex) {
        logger.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.RNF);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(Exception ex) {
        logger.error(ex.getMessage());
        BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
        Map<String, String> errors = new HashMap<String, String>();
        for (ObjectError objError : result.getAllErrors()) {
            FieldError fieldError = (FieldError)objError;
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse("The given data was invalid", errors);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(Exception ex) {
        String errMsg = ex.getMessage() != null ? ex.getMessage() : "";
        logger.error(errMsg);
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> missingRequestHeaderException(Exception ex) {
        String errMsg = ex.getMessage() != null ? ex.getMessage() : "";
        logger.error(errMsg);
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> feignException(FeignException ex) {
        String errMsg = ex.getMessage() != null ? ex.getMessage() : "";
        logger.error(errMsg);
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        HttpStatus httpStatus = HttpStatus.valueOf(ex.status());
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> unprocessableEntityException(Exception ex) {
        String errMsg = ex.getMessage() != null ? ex.getMessage() : "";
        logger.error(errMsg);
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> genericException(Exception ex) {
        String errMsg = ex.getMessage() != null ? ex.getMessage() : "";
        if (errMsg.isBlank()) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        } else {
            logger.error(errMsg);
        }
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ex.printStackTrace();
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }

}
