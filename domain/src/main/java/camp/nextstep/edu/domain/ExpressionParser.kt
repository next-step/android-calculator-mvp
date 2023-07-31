package camp.nextstep.edu.domain

object ExpressionParser {
    fun parse(expression: String): List<String> = expression.split(" ")
}