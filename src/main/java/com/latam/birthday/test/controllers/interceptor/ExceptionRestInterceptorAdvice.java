package com.latam.birthday.test.controllers.interceptor;

import com.latam.birthday.test.exceptions.BusinessException;
import com.latam.birthday.test.helpers.StandarResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionRestInterceptorAdvice {

    /**
     *
     *      Interceptor if some problem with api parameters
     *
     * @param ex HttpMessageNotReadableException
     * @return ResponseEntity
     * @author Jorge Silva Aguilera
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> HttpMessageHandler(HttpMessageNotReadableException ex) {
        String msg = "Invalid or missing parameters";
        return new ResponseEntity<>(StandarResponseHelper.error(msg), HttpStatus.BAD_REQUEST);

    }


    /**
     *
     *      Interceptor for custom validation for api parameters
     *
     * @param exarg MethodArgumentNotValidException
     * @return ResponseEntity
     * @author Jorge Silva Aguilera
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> paramValidationHandler(MethodArgumentNotValidException exarg){

        String msg = "Validation failed for some parameters";

        return new ResponseEntity<>(
            StandarResponseHelper.error(exarg.getBindingResult().getFieldErrors() != null ? exarg.getBindingResult().getFieldErrors() : msg ),
            HttpStatus.BAD_REQUEST
        );
    }



    /**
     *
     *      Interceptor for specific business Exception in code
     *
     * @param exarg BusinessException
     * @return ResponseEntity
     * @author Jorge Silva Aguilera
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> BusinesHandler(BusinessException exarg) {

        String msg = "Business logic error";
        return new ResponseEntity<>(
                StandarResponseHelper.error(exarg.getMessage() != null ? exarg.getMessage() : msg),
                HttpStatus.OK
        );
    }




    /**
     *
     *      Interceptor Internal errors
     *
     * @param exarg Exception
     * @return ResponseEntity
     * @author Jorge Silva Aguilera
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> internalHandler(Exception exarg) {

        exarg.printStackTrace();

        String msg = "Some operation cant carry out";
        return new ResponseEntity<>(
                StandarResponseHelper.error(exarg.getMessage() != null ? exarg.getMessage() : msg),
                HttpStatus.OK
        );
    }
}
