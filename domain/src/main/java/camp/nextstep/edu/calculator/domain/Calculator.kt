package camp.nextstep.edu.calculator.domain

object Calculator {
    private const val SEPERATOR = " "
    private const val DEFAULT_POSITON = 0
    private const val NEXT_NUMBER_INDEX = 2
    private const val EXTRA_ITEM_COUNT = 1

    fun evaluate(formulaString: String?): String {
        require(!formulaString.isNullOrEmpty() && formulaString.trim() != "") {
            "Input value is empty"
        }
        val formulas = formulaString.split(SEPERATOR)
        check(formulas.size / NEXT_NUMBER_INDEX == EXTRA_ITEM_COUNT) {
            "Formulas must have odd size Items"
        }
        return calculate(formulas)
    }

    private fun calculate(formulas: List<String>): String {
        var index = DEFAULT_POSITON
        var resultNumber = formulas[index]
        require(resultNumber.isNum()) {
            "Parameter must be Number"
        }
        while(formulas.size > index + NEXT_NUMBER_INDEX) {
            index = index.inc()
            val operator = formulas[index]
            index = index.inc()
            val postNumber = formulas[index]
            require(postNumber.isNum()) {
                "Parameter must be Number"
            }
            resultNumber = Operator.getOperator(operator)
                .calculate(resultNumber, postNumber)
        }
        return resultNumber
    }

    private fun String.isNum(): Boolean {
        this.toIntOrNull() ?: false
        return true
    }
}
