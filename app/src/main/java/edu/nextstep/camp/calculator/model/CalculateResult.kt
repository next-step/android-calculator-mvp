package edu.nextstep.camp.calculator.model

@JvmInline
value class CalculateResult(private val value: String) {
    override fun toString(): String = "= $value"
}