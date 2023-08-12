package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.ExpressionItems
import camp.nextstep.edu.calculator.domain.InputTextConvertor

class ExpressionPresenter(
    private val view: Contract.View,
    private val expression: Expression = Expression(InputTextConvertor())
) : Contract.Presenter {

    private var expressionItems = ExpressionItems(emptyList())

    override fun addExpressionText(text: String) {
        expressionItems = expression.addExpression(expressionItems, text)
        view.display(expressionItems.getText())
    }

    override fun removeExpressionItem() {
        expressionItems = expression.removeExpressionItem(expressionItems)
        view.display(expressionItems.getText())
    }

    override fun calculate() {
        val expressionText = expressionItems.getText()
        Calculator(InputTextConvertor()).evaluate(expressionText)?.let {
            view.display(it.toString())
        } ?: view.displayExpressionError()
    }
}
