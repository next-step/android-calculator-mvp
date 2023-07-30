package com.nextstep.edu.domain

import java.lang.IllegalArgumentException

sealed class Operators {

    abstract val method: String?
    abstract fun calculate(first: Int, second: Int): Int

    object Add: Operators() {
        override val method: String = "+"
        override fun calculate(first: Int, second: Int): Int = first + second
    }

    object Minus: Operators() {
        override val method: String = "-"
        override fun calculate(first: Int, second: Int): Int = first - second
    }

    object Multiply: Operators() {
        override val method: String = "*"
        override fun calculate(first: Int, second: Int): Int = first * second
    }

    object Divider: Operators() {
        override val method: String = "/"
        override fun calculate(first: Int, second: Int): Int = first / second
    }

    object Unknown: Operators() {
        override val method: String? = null
        override fun calculate(first: Int, second: Int): Int = throw IllegalArgumentException("Unsupported Methods")
    }

    companion object {
        fun toCalculate(method: String, first: String, second: String): Int {
            return Operators::class.sealedSubclasses
                .map { it.objectInstance as Operators }
                .find { it.method == method }
                ?.calculate(first.toInt(), second.toInt())
                ?: throw IllegalArgumentException("Unsupported Methods")
        }
    }

}