package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.expression.Expression
import edu.nextstep.camp.calculator.domain.expression.Expressions

class Calculator {

    @Throws(IllegalArgumentException::class)
    fun calculate(text: String?): Double {
        require(!text.isNullOrBlank()) { "잘못된 요청입니다. : $text" }
        return getExpressions(getArrangedRequest(text)).calculate()
    }

    @Throws(IllegalArgumentException::class)
    fun evalute(text: String?): String {
        require(!text.isNullOrBlank()) { "잘못된 요청입니다. : $text" }
        return getExpressions(getArrangedRequest(text)).evalute()
    }

    private fun getArrangedRequest(text: String): String = if (text[0] == '-') "0$text" else text

    private fun getExpressions(text: String): Expressions {
        return Expressions(
            Expression.merge(Expression.getNumbers(text), Expression.getOperators(text))
        )
    }
}
