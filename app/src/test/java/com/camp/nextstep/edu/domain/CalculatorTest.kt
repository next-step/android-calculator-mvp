package com.camp.nextstep.edu.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {
    @Test
    fun evaluatesExpression() {
        val calculator = Calculator()
        val actual: Int = calculator.evaluate("1 + 2 + 3")
        //assertEquals(6, actual)
        assertThat(actual).isEqualTo(6)
    }
}