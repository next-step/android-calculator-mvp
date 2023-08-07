package camp.nextstep.edu.calculator.domain

class Calculator(private val convertor: InputTextConvertor) {
    fun evaluate(input: String?): Int {
        val splitArray = convertor.getSplitStrings(input)
        var operation: Operation? = null
        var evaluateValue = 0

        splitArray.forEachIndexed { index, str ->
            if (index % 2 == 0) {
                evaluateValue = evaluate(
                    operation, evaluateValue, convertor.getNumberText(str)
                )
            } else {
                operation = convertor.getOperationText(str)
            }
        }
        return evaluateValue
    }

    private fun evaluate(operation: Operation?, evaluateValue: Int, value: Int): Int {
        return operation?.run {
            evaluate(evaluateValue, value)
        } ?: value
    }
}
