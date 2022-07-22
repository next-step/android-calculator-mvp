package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
    }

    interface Presenter {
        fun input(operand: Int)
        fun input(operator: Operator)
        fun removeLast()
        fun calculate(): Int?
        fun initExpression(result : Int)
    }
}
