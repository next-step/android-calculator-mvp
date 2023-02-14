package camp.nextstep.edu.calculator


interface CalculatorViewInterface {
    var presenter: CalculatorPresenterInterface

    fun showCalculationResult(result: String)
    fun alertIncompleteExpression()
    fun showCalculationExpression(expression: String)
}