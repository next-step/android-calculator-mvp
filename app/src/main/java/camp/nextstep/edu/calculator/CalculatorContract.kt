package camp.nextstep.edu.calculator

interface CalculatorContract {
    interface View {
        var presenter: Presenter

        fun inCompleteExpressionNotice()
        fun showExpressions(expression: String)
    }

    interface Presenter {
        fun appendExpression(newText: String)
        fun removeExpression()
        fun calculate()
    }
}
