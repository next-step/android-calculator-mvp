package camp.nextstep.edu.domain.calculator


object ExpressionValidator {

    fun validOrError(expression: List<ExpressionItem>): Boolean {
        require(expression.isNotEmpty()) { "공백은 입력할 수 없습니다." }
        require(isValidHelper(expression)) { "유효하지 않은 표현식입니다." }
        return true
    }

    private fun isValidHelper(
        expression: List<ExpressionItem>
    ): Boolean {
        if (expression.size % 2 == 0) return false
        if (expression.first() !is Num) return false
        if (expression.last() !is Num) return false

        expression.reduce { acc, s ->
            if (acc is Num && s is Num)
                return false

            if (acc !is Num && s !is Num)
                return false

            s
        }

        return true
    }
}
