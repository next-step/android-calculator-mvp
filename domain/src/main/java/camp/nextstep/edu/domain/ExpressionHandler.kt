package camp.nextstep.edu.domain

class ExpressionHandler {

    var expression = ""
        private set

    fun addInputValue(inputValue: String) {
        expression += inputValue
    }

    fun deleteLast() {
        expression = expression.dropLast(1)
    }
}