package camp.nextstep.edu.calculator

import com.nextstep.edu.domain.Operator

interface CalculatorPresenterInterface {
    fun appendExpression(operand: Int)
    fun appendExpression(operator: Operator)
    fun removeExpression()
    fun calculate()
    fun clear(value: Int)
}