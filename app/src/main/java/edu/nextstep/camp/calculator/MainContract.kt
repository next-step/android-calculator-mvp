package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 20..
 */
interface MainContract {
    interface View {
        fun showExpression(expression: String)
    }

    interface Presenter {
        fun enterNumber(number: Int)
        fun enterOperator(operator: Operator)
        fun delete()
    }
}