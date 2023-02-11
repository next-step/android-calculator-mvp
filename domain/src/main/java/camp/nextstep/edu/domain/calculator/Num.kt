package camp.nextstep.edu.domain.calculator


data class Num(
    val value: Int
) : ExpressionItem {

    override fun toString() = value.toString()
}
