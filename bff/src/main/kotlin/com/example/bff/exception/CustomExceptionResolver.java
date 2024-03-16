package com.example.bff.exception;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.concurrent.CompletableFuture;

@Component
public class CustomExceptionResolver implements DataFetcherExceptionHandler {

    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof WebExchangeBindException) {
            System.err.println("This is an error message");
        } else {
            return null;
        }
        return null;
    }

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        System.err.println("This is an error message");
        return null;
    }
}