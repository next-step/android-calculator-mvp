package camp.nextstep.edu.domain

class Validator {

    fun operateIfValidate(
        operationType: OperationType,
        onExecutionError: (() -> Unit)? = null,
        operation: () -> Unit,
    ) {
        when (operationType) {
            is OperationType.ConcatNumberOperation -> validateConcatNumberOperation(
                number = operationType.number,
                expression = operationType.expression,
                operation = operation
            )

            is OperationType.BasicOperation -> validateBaseOperation(
                expression = operationType.expression,
                basicOperator = operationType.basicOperator,
                operation = operation
            )

            is OperationType.ExecuteOperation -> validateExecuteOperation(
                expression = operationType.expression,
                operation = operation,
                onError = onExecutionError
            )
        }
    }

    private fun validateConcatNumberOperation(
        number: Int?,
        expression: String,
        operation: () -> Unit
    ) {
        if (number == null) throw IllegalArgumentException()
        if (expression.isEmpty() && number == 0) return
        operation()
    }

    private fun validateBaseOperation(
        expression: String,
        basicOperator: String?,
        operation: () -> Unit
    ) {
        if (basicOperator.isNullOrEmpty()) throw IllegalArgumentException()
        if (isLastExpressionIsNumber(expression)) operation()
    }

    private fun validateExecuteOperation(
        expression: String,
        operation: () -> Unit,
        onError: (() -> Unit)? = null
    ) {
        if (isLastExpressionIsNumber(expression).not()) {
            onError?.let { it() }
            return
        }

        if (isLastExpressionIsNumber(expression) && expression.isNotEmpty()) {
            operation()
        }
    }

    private fun isLastExpressionIsNumber(expression: String): Boolean {
        return expression.lastOrNull().toString().toIntOrNull() != null
    }

    sealed class OperationType {
        data class ConcatNumberOperation(
            val number: Int?,
            val expression: String
        ) : OperationType()

        data class BasicOperation(
            val expression: String,
            val basicOperator: String?
        ) : OperationType()

        data class ExecuteOperation(
            val expression: String
        ) : OperationType()
    }
}