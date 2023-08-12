package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation
import camp.nextstep.edu.calculator.domain.ExpressionItems
import camp.nextstep.edu.calculator.domain.InputTextConvertor
import camp.nextstep.edu.calculator.domain.plus

class ExpressionPresenter(
    private val view: Contract.View,
    private var expressionItems: ExpressionItems = ExpressionItems(emptyList())
) : Contract.Presenter {

    override fun addOperandExpression(operand: Operand) {
        expressionItems += operand
        view.display(expressionItems.getText())
    }

    override fun addOperationExpression(operation: Operation) {
        expressionItems += operation
        view.display(expressionItems.getText())
    }

    override fun removeExpressionItem() {
        expressionItems = expressionItems.removeLastExpression()
        view.display(expressionItems.getText())
    }

    override fun calculate() {
        val expressionText = expressionItems.getText()

        Calculator(InputTextConvertor()).evaluate(expressionText)?.let {
            expressionItems = ExpressionItems(listOf(Operand(it)))
            view.display(it.toString())
        } ?: view.displayExpressionError()
    }
}
