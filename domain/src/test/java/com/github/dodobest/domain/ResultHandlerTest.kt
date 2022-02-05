package com.github.dodobest.domain

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class ResultHandlerTest {
    private lateinit var resultHandler: ResultHandler

    @Before
    fun setUp() {
        resultHandler = ResultHandler()
    }

    @Test
    fun `ResultHandler에 Expression(3+3, 5)와 Expression(1+1, 2)를 add하면 ResultHandler의 results list에 추가된다`() {
        // when
        resultHandler.add("3+3", "5")
        resultHandler.add("1+1", "2")
        val actual: List<Result> = resultHandler.getResults()

        // then
        Truth.assertThat(actual).isEqualTo(arrayListOf(Result("3+3", "5")
            , Result("1+1", "2")))
    }
}