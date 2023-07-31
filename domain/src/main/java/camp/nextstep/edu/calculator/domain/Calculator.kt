package camp.nextstep.edu.calculator.domain

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
        require(!text.isNullOrBlank()) { "null Or Empty" }
        return text
    }

    private fun getOperation(operationText: String): Operations {
        val operation = Operations.findOperation(operationText)
        require(operation != null) { "no Operations" }
        return operation
    }

    companion object {
        private const val splitText = " "
    }
}
