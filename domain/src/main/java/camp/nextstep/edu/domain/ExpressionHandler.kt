package camp.nextstep.edu.domain

class ExpressionHandler {

    private var expression = ""

    fun getExpression() = expression

    fun addInputValue(inputValue: String) {
        expression += inputValue
    }

    fun deleteLast() {
        expression = expression.dropLast(1)
    }
}