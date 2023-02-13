package camp.nextstep.edu.calculator

interface CalculatorContract {
    interface View {
        var presenter: Presenter
        fun showExpression(expression: String)
        fun showIncompleteExpressionError()
    }

    interface Presenter {
        fun updateExpressions(input: String)
        fun deleteLast()
        fun calculateAndReset()
    }

}