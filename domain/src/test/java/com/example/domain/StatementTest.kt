package com.example.domain

import org.junit.Assert.*
import org.junit.Test

class StatementTest {

    @Test
    fun `빈_문자열의_수식이_나온다`() {
        // When
        val statement = Statement()

        // Then
        assertEquals(Statement(emptyList()), statement)
        assertEquals("", statement.termsToString())
    }

    @Test
    fun `""일때_1_버튼을_누르면_"1"_반환한다`() {
        // Given
        val statement = Statement()

        // When
        statement.addTerm(Operand(1))

        // Then
        assertEquals("1", statement.termsToString())
        assertEquals(Statement(listOf(Operand(1))), statement)
    }

    @Test
    fun `"5_+_"일때_1_버튼을_누르면_"5_+_1"_반환한다`() {
        // Given
        val statement = Statement(OperationParser.parse("5 +"))

        // When
        statement.addTerm(Operand(1))

        // Then
        assertEquals("5 + 1", statement.termsToString())
        assertEquals(Statement(listOf(Operand(5), Operator.ADD, Operand(1))), statement)
    }

    @Test
    fun `""일때_연산자_버튼을_누르면_아무_변화없다`() {
        // Given
        val statement = Statement(emptyList())

        // When
        statement.addTerm(Operator.ADD)

        // Then
        assertEquals("", statement.termsToString())
        assertEquals(Statement(emptyList()), statement)
    }

    @Test
    fun `"1"일때_연선자_버튼을_누르면_"1_+"_반환한다`() {
        // Given
        val statement = Statement(OperationParser.parse("1"))

        // When
        statement.addTerm(Operator.ADD)

        // Then
        assertEquals("1 +", statement.termsToString())
        assertEquals(Statement(emptyList()), statement)
    }

    @Test
    fun `"1_+"일때_빼기_연산자_버튼을_누르면_"1_-"_반환한다`() {
        // Given
        val statement = Statement(OperationParser.parse("1"))

        // When
        statement.addTerm(Operator.ADD)

        // Then
        assertEquals("1 +", statement.termsToString())
        assertEquals(Statement(emptyList()), statement)
    }
}