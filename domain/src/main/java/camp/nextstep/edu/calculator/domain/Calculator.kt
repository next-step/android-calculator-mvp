package camp.nextstep.edu.calculator.domain

object Calculator {

    private const val DEFAULT_POSITION = 0
    private const val NEXT_INDEX_STEP = 2
    private const val FIRST_INDEX = 1

    fun evaluate(expression: Expression): String {
        return calculate(expression.formulas)
    }

    private fun calculate(formulas: List<String>): String {
        var resultNumber = formulas[DEFAULT_POSITION]
        for (index: Int in FIRST_INDEX until formulas.size step NEXT_INDEX_STEP) {
            val operator = formulas[index]
            val postNumber = formulas[index.inc()]
            resultNumber = Operator.getOperator(operator)
                .calculate(resultNumber, postNumber)
        }
        return resultNumber
    }
}
