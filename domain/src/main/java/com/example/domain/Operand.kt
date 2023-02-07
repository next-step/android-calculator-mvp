package com.example.domain

import java.math.BigDecimal

@JvmInline
value class Operand(private val value: BigDecimal) {
    constructor(value: Int) : this(value.toBigDecimal())
    constructor(value: Double) : this(value.toBigDecimal())

    operator fun plus(other: Operand): Operand = Operand(value.add(other.value))

    operator fun minus(other: Operand): Operand = Operand(value.subtract(other.value))

    operator fun times(other: Operand): Operand = Operand(value.multiply(other.value))

    operator fun div(other: Operand): Operand = Operand(value.divide(other.value))

}
