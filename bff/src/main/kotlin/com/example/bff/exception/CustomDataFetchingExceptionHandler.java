package com.example.bff.exception;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.concurrent.CompletableFuture;


@Component
public class CustomDataFetchingExceptionHandler implements DataFetcherExceptionHandler {

    public String handleValidationException(ValidationException e) {
        System.err.println("This is an error message");
        return e.toString();
    }

    public String handleWebExchangeBindException(WebExchangeBindException e) {
        System.err.println("This is an error message");
        return e.toString();
    }

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof RuntimeException) {
            System.err.println("This is an error message");
        }
        return null;
    }
}