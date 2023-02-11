package camp.nextstep.edu.domain.calculator


sealed class Operators : ExpressionItem {

    protected abstract val symbol: String

    object Plus : Operators() {
        override val symbol = "+"
        override fun calculate(first: Int, second: Int) = first + second
        override fun toString() = symbol
    }

    object Minus : Operators() {
        override val symbol = "-"
        override fun calculate(first: Int, second: Int) = first - second
        override fun toString() = symbol
    }

    object Multiply : Operators() {
        override val symbol = "*"
        override fun calculate(first: Int, second: Int) = first * second
        override fun toString() = symbol
    }

    object Divide : Operators() {
        override val symbol = "/"
        override fun calculate(first: Int, second: Int) = first / second
        override fun toString() = symbol
    }

    abstract fun calculate(first: Int, second: Int): Int


    companion object {
        fun of(symbol: String) =
            Operators::class.sealedSubclasses
                .map { it.objectInstance as Operators }
                .find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unsupported Operation")
    }
}
