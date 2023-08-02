package camp.nextstep.edu.calculator.domain

class ExpressionStorage {
    private val formulas = mutableListOf<String>()
    fun add(formula: String): String {
        formulas.add(formula)
        return formulas.joinToString("")
    }

    fun removeLast(): String {
        if(formulas.size > 0) {
            formulas.removeLast()
        }
        return formulas.joinToString("")
    }

    fun getFormularString(): String {
        val formularString = formulas.joinToString("")
        return formularString
    }
}