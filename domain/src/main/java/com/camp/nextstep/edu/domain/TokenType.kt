package com.camp.nextstep.edu.domain
enum class TokenType(
    val operator: String,
    val operation: (Int, Int) -> Int,
) {
    Plus("+", { x, y -> x + y }),
    Minus("-", { x, y -> x - y }),
    Multiply("*", { x, y -> x * y }),
    Divide("/", { x, y -> x / y })
}