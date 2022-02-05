package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View{

        fun refreshCount(expression: Expression)
    }

    interface Presenter{

        fun inputNumber(value: Int)
        fun inputOperator(operator: Operator)
        fun removeLast()
    }
}