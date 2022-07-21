package edu.nextstep.camp.calculator

import android.widget.Toast
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 20..
 */
class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun enterNumber(number: Int) {
        expression += number

        view.showExpression(expression.toString())
    }

    override fun enterOperator(operator: Operator) {
        expression += operator

        view.showExpression(expression.toString())
    }

    override fun delete() {
        expression = expression.removeLast()

        view.showExpression(expression.toString())
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString()) ?: return
// TODO 오류 메세지
//        if (result == null) {
//            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
//            return@setOnClickListener
//        }
        expression = Expression.EMPTY + result

        view.showExpression(expression.toString())
    }
}