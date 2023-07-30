package com.example.domain

fun String.toIntOrThrow(): Int {
	return this.toIntOrNull() ?: throw IllegalStateException()
}