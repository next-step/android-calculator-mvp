package camp.nextstep.edu.domain

const val CAST_INT_EXCEPTION = "cast int exception"

fun String.toIntOrThrow(): Int {
	return this.toIntOrNull() ?: throw IllegalStateException(CAST_INT_EXCEPTION)
}