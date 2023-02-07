package com.example.domain

import junit.framework.TestCase
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

    @Test
    fun `""일때 지우기_버튼을_누르면_아무변화가_없다`() {
        // Given
        val statement = Statement(emptyList())

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(emptyList()), statement)
    }

    @Test
    fun `"32_+"일때_지우기_버튼을_누르면_32가_된다`() {
        // Given
        val statement = Statement(OperationParser.parse("32 +"))

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(OperationParser.parse("32")), statement)
        assertEquals("32", statement.termsToString())
    }

    @Test
    fun `두자리_수_이상의_피연산자(32)일_때_지우기_버튼을_누르면_한자릿_수의_숫자(3)가_된다`() {
        // Given
        val statement = Statement(OperationParser.parse("32"))

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(OperationParser.parse("3")), statement)
        assertEquals("3", statement.termsToString())
    }

    @Test
    fun `한자리_수의_피연산자(3)일_때_지우기_버튼을_누르면_공백이_된다`() {
        // Given
        val statement = Statement(OperationParser.parse("3"))

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(emptyList()), statement)
        assertEquals("", statement.termsToString())
    }

    @Test
    fun `"3_+_2"일때_계산_버튼을_누르면_5를_반환한다`() {
        // GIVEN
        val statement = Statement(OperationParser.parse("3 + 2"))

        // When
        val result = statement.calculate()

        // Then
        assertEquals(5, result)
    }

    @Test
    fun `"3_+"일떄_계산_버튼을_누르면_"완성되지_수식_입니다"_에러를_반환한다`() {
        // GIVEN
        val statement = Statement(OperationParser.parse("3 +"))

        val exception = assertThrows(IllegalArgumentException::class.java) {
            statement.calculate()
        }
        assertEquals("완성되지 않은 수식입니다.", exception.message)
    }
}