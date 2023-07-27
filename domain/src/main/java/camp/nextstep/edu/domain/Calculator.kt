package camp.nextstep.edu.domain

class Calculator {

    private val validator by lazy { Validator() }

    private var expressions = ""

    fun addNumberOnExpressions(number: Int?) {
        validator.operateIfValidate(
            Validator.OperationType.ConcatNumberOperation(
                number = number,
                expression = expressions
            )
        ) {
            expressions += "$number"
        }
    }

    fun addBasicOperationOnExpression(basicOperator: String?) {
        validator.operateIfValidate(
            Validator.OperationType.BasicOperation(
                basicOperator = basicOperator,
                expression = expressions
            )
        ) {
            expressions += " $basicOperator "
        }
    }

    fun execute() {
        validator.operateIfValidate(
            operationType = Validator.OperationType.ExecuteOperation(
                expression = expressions
            ),
        ) {
            val expressionPiece = expressions.split(" ")
            var result = expressionPiece.first().toLong()

            for (i in 1 until  expressionPiece.size step 2) {
                result = expressionPiece[i].getExecutionResult(result, expressionPiece[i + 1].toLong())
            }

            expressions = result.toString()
        }
    }

    private fun String.getExecutionResult(firstNumber: Long, secondNumber: Long): Long {
        return when (this) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "÷" -> firstNumber / secondNumber
            "×" -> firstNumber * secondNumber
            else -> throw IllegalArgumentException()
        }
    }

    fun deleteExpression() {
        val expression = expressions.trimEnd().dropLast(1)
        expressions =
            if (expression.endsWith(" ")) expression.trimEnd()
            else expression
    }

    fun getExpressions() = expressions
}