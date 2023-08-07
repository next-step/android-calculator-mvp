package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.ExpressionItems
import camp.nextstep.edu.calculator.domain.InputTextConvertor

class ExpressionPresenter(
    private val view: Contract.View,
    private val expression: Expression = Expression(InputTextConvertor(), ExpressionItems())
) : Contract.Presenter {
    override fun addExpressionText(text: String) {
        view.display(expression.addExpression(text))
    }

    override fun removeExpressionItem() {
        view.display(expression.removeExpressionItem())
    }

    override fun calculate() {
        view.display(expression.calculate())
    }
}
