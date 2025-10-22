package org.example.miniproject.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BoardNotFoundException.class)
    public String boardNotFoundException(Exception e, Model model){

        return "redirect:/list";
    }

    @ExceptionHandler(value = PasswordMismatchException.class)
    public String passwordMismatchException(Exception e, Model model){

        return "redirect:/list";
    }

}
