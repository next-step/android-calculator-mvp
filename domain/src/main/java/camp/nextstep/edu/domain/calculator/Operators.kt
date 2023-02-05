package camp.nextstep.edu.domain.calculator


sealed class Operators : ExpressionItem {
    object Plus : Operators() {
        override fun calculate(first: Int, second: Int) = first + second
    }

    object Minus : Operators() {
        override fun calculate(first: Int, second: Int) = first - second
    }

    object Multiply : Operators() {
        override fun calculate(first: Int, second: Int) = first * second
    }

    object Divide : Operators() {
        override fun calculate(first: Int, second: Int) = first / second
    }

    abstract fun calculate(first: Int, second: Int): Int


    companion object {
        fun of(symbol: String) =
            when (symbol) {
                "+" -> Plus
                "-" -> Minus
                "*" -> Multiply
                "/" -> Divide
                else -> throw IllegalArgumentException("Unsupported Operation")
            }
    }
}
