package camp.nextstep.edu.calculator.domain

import java.lang.IllegalArgumentException

class Calculator {
    private var evaluateValue = 0

    fun evaluate(input: String?): Int {
        val splitArray = splitInputText(input)
        var operation: Operations? = null

        splitArray.forEachIndexed { index, str ->
            if (index % 2 == 0) {
                evaluate(operation, str.trim().toInt())
            } else {
                operation = getOperation(str.trim())
            }
        }
        return evaluateValue
    }

    private fun evaluate(operation: Operations?, value: Int) {
        evaluateValue = operation?.run {
            evaluate(evaluateValue, value)
        } ?: value
    }

    private fun splitInputText(input: String?): List<String> {
        val inputStr = checkEmpty(input)
        return inputStr.split(splitText)
    }

    private fun checkEmpty(text: String?): String {
        if (text.isNullOrBlank()) throw IllegalArgumentException("null Or Empty")
        return text
    }

    private fun getOperation(operationText: String): Operations {
        return Operations.toOperation(operationText)
            ?: throw IllegalArgumentException("no Operations")
    }

    companion object {
        private const val splitText = " "
    }
}
