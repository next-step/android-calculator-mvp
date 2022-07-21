package edu.nextstep.camp.calculator

/**
 * Created by link.js on 2022. 07. 20..
 */
interface MainContract {
    interface View {
        fun showExpression(expression: String)
    }

    interface Presenter {
        fun enterNumber(number: Int)
    }
}