package camp.nextstep.edu.calculator

interface CalculatorContract {
    interface View {
        fun inCompleteExpressionNotice()
        fun showExpressions(expression: String)
        fun showCalculationResult(expression: String, result: Double) : String
    }

    interface Presenter {
        fun appendExpression(newText: String)
        fun removeExpression()
        fun calculate()
    }
}
