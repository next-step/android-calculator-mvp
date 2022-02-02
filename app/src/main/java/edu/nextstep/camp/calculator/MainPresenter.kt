package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression = Expression.EMPTY

    override fun addOperand(rawOperand: String) {
        expression = expression.addOperand(rawOperand)
        view.refreshExpression(expression.rawExpression)
    }

    override fun addOperator(rawOperator: String) {
        expression = expression.addOperator(rawOperator)
        view.refreshExpression(expression.rawExpression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.refreshExpression(expression.rawExpression)
    }

    override fun calculate() {
        try {
            view.showResult(expression.getResult().toString())
        } catch (e: IllegalArgumentException) {
            view.showToast("완성되지 않은 수식입니다")
        }
    }
}