package com.example.domain

enum class Operator(val op: String) {
	PLUS("+"), MINUS("-"), MUL("*"), DIV("/");

	companion object {
		fun getOrThrow(op: String): Operator {
			return Operator.values().find { operator ->
				operator.op == op
			} ?: throw IllegalArgumentException()
		}
	}
}