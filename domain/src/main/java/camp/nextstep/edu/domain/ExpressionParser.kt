package camp.nextstep.edu.domain

class ExpressionParser {
    companion object {
        fun parse(expression: String): List<String> = expression.split(" ")
    }
}