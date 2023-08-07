package com.camp.nextstep.edu.domain

sealed class TokenType(val operator: String)  {
    object Plus: TokenType("+")
    object Minus: TokenType("-")
    object Multiply: TokenType("*")
    object Divide: TokenType("/")
}
