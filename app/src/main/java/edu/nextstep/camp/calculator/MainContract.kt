package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * MainActivity에 대한 contract
 * Created by jeongjinhong on 2022. 07. 22..
 */
interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showIncompleteExpressionToast()
        fun showCalculationMemory(totalRecord: String)
    }

    interface Presenter {
        fun addOperand(operand: Int)
        fun addOperator(operator: Operator)
        fun removeLast()
        fun calculateExpression()
        fun showCalculationMemory()
    }
}