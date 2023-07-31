package camp.nextstep.edu.calculator.domain

class Calculator(private val convertor: InputTextConvertor) {
    private var evaluateValue = 0

    fun evaluate(input: String?): Int {
        val splitArray = splitInputText(input)
        var operation: Operations? = null

        splitArray.forEachIndexed { index, str ->
            if (index % 2 == 0) {
                evaluate(operation, convertor.getNumberText(str))
            } else {
                operation = convertor.getOperationText(str)
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
        val inputStr = convertor.getNotEmptyText(input)
        return inputStr.split(splitText)
    }

    companion object {
        private const val splitText = " "
    }
}
