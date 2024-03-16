package com.example.bff.exception

import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.GraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Path
import mu.KLogging
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture


@Component
class ConstraintViolationExceptionHandler : DataFetcherExceptionHandler {

    companion object: KLogging()

    override fun handleException(handlerParameters: DataFetcherExceptionHandlerParameters?): CompletableFuture<DataFetcherExceptionHandlerResult> {
        val exception = handlerParameters?.exception
        if (exception is ConstraintViolationException) {
            val errorMap: MutableMap<String, MutableList<String>> = HashMap()
            exception.constraintViolations.forEach { violation ->
                val message = violation.message
                if (message != null) {
                    errorMap.getOrPut(getField(violation.propertyPath) , defaultValue = { ArrayList() }).add(message)
                }
            }

            val result = DataFetcherExceptionHandlerResult.newResult()
                .error(createGraphQLError(errorMap))
                .build()

            return CompletableFuture.completedFuture(result)
        }
        return this.handleException(handlerParameters)
    }

    private fun createGraphQLError(
        errorMap: Map<String, List<String>>
    ): GraphQLError {
        return TypedGraphQLError.newInternalErrorBuilder()
            .message("A ConstraintViolationExceptionHandler has occured. Please view the debugInfo for more informations")
            .debugInfo(errorMap)
            .build()
    }

    fun getField(propertyPath: Path): String {
        val parts = propertyPath.toString().split('.')
        return parts.last()
    }
}
