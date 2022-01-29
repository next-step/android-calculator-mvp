package edu.nextstep.camp.calculator.model

@JvmInline
value class Result(private val value: String) {
    override fun toString(): String = "= $value"
}