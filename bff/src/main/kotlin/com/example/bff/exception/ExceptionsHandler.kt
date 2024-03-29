package com.example.bff.exception

import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import graphql.execution.SimpleDataFetcherExceptionHandler
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Path
import mu.KLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.util.concurrent.CompletableFuture

@Component
class ExceptionsHandler: DataFetcherExceptionHandler {

    companion object: KLogging()

    override fun handleException(handlerParameters: DataFetcherExceptionHandlerParameters?): CompletableFuture<DataFetcherExceptionHandlerResult> {
        val exception = handlerParameters?.exception

        if(exception is WebClientResponseException) {
            val errors = exception.getResponseBodyAs(Map::class.java) as Map<String, List<String>>?
            val graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                .message(errors.toString())
                .debugInfo(errors)
                .build()
            val result = DataFetcherExceptionHandlerResult.newResult()
                .error(graphqlError)
                .build()
            return CompletableFuture.completedFuture(result)
        }
        else if(exception is ConstraintViolationException) {
            val errorMap: MutableMap<String, MutableList<String>> = HashMap()
            exception.constraintViolations.forEach { violation ->
                val message = violation.message
                if (message != null) {
                    errorMap.getOrPut(getFieldName(violation.propertyPath) , defaultValue = { ArrayList() }).add(message)
                }
            }
            val graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                .message(errorMap.toString())
                .debugInfo(errorMap as Map<String, List<String>>?)
                .build()
            val result = DataFetcherExceptionHandlerResult.newResult()
                .error(graphqlError)
                .build()
            return CompletableFuture.completedFuture(result)
        }
        throw RuntimeException("*** ExceptionsHandler RuntimeException: $exception")
    }

    fun getFieldName(propertyPath: Path): String {
        val parts = propertyPath.toString().split('.')
        return parts.last()
    }
}
