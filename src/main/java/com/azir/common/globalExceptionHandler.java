package com.azir.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class globalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        System.out.println(ex.getMessage());
        return R.error("失败了");
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public R<String> arrayexceptionHandler(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
        return R.error("数组越界");
    }
}
