package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculationRecord
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * MainActivity에 대한 Presenter
 * Created by jeongjinhong on 2022. 07. 22..
 */
class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator(),
    private val calculationRecord: CalculationRecord = CalculationRecord()
) : MainContract.Presenter {

    override fun addOperand(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionToast()
            return
        }
        calculationRecord.addCalculationRecord(expression.toString(), result)
        expression = Expression.EMPTY + result
        view.showExpression(expression)
    }

    override fun clickCalculationMemory(isMemoryVisible: Boolean) {
        if (isMemoryVisible) {
            view.showCalculationRecord(false)
        } else {
            view.showCalculationRecord(true)
            view.showCalculationMemory(calculationRecord.calculationRecordList)
        }
    }

}