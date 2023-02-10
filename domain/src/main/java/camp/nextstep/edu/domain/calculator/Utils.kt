package camp.nextstep.edu.domain.calculator


private val numericRegex by lazy { "-?[0-9]+(\\.[0-9]+)?".toRegex() }

fun String.isNumeric() = matches(numericRegex)
