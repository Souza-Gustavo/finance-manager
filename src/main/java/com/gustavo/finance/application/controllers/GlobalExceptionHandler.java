package com.gustavo.finance.application.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.stereotype.Component;

@ControllerAdvice
@Component("globalExceptionHandlerControllers") // nome único para evitar conflito
public class GlobalExceptionHandler {

}
