package com.nextstep.edu.domain

import java.lang.IllegalArgumentException

sealed class Operators {

    abstract fun calculate(): Int

    class Add(private val first: Int, private val second: Int): Operators() {
        override fun calculate(): Int = first + second
    }

    class Minus(private val first: Int, private val second: Int): Operators() {
        override fun calculate(): Int = first - second
    }

    class Multiply(private val first: Int, private val second: Int): Operators() {
        override fun calculate(): Int = first * second
    }

    class Divider(private val first: Int, private val second: Int): Operators() {
        override fun calculate(): Int = first / second
    }

    object Unknown: Operators() {
        override fun calculate(): Int = throw IllegalArgumentException("Unsupported Methods")
    }

    companion object {
        fun List<String>.toCalculate(): Int {
            val method = this[1]
            val first = this[0].toInt()
            val second = this[2].toInt()

            return when(method) {
                "+" -> Add(first, second).calculate()
                "-" -> Minus(first, second).calculate()
                "*" -> Multiply(first, second).calculate()
                "/" -> Divider(first, second).calculate()
                else -> Unknown.calculate()
            }
        }
    }

}