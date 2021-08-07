package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {

    }

    interface Presenter {

        fun expression(number: Int): Expression

        fun expression(operator: Operator): Expression
    }

}
