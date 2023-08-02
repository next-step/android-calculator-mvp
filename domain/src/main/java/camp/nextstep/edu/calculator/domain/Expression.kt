package camp.nextstep.edu.calculator.domain

class Expression(formulaString: String?) {

    private val formulas: List<String>
    init {
        require(!formulaString.isNullOrEmpty() && formulaString.trim() != "") {
            "Input value is empty"
        }
        formulas = formulaString.split(SEPERATOR)
        check(formulas.size % NUMBER_INDEX_STEP == EXTRA_ITEM_COUNT) {
            "Formulas must have odd size Items"
        }

        formulas.withIndex()
            .filter { (index, formula) -> index % NUMBER_INDEX_STEP == 0}
            .map { (index, formula) ->
                require(formula.isNum()) {
                    "Parameter must be Number"
                }
            }
    }

    private fun String.isNum(): Boolean {
        this.toIntOrNull() ?: return false
        return true
    }

    fun getExpressions(): List<String> {
        return formulas
    }

    companion object {
        private const val SEPERATOR = " "
        private const val EXTRA_ITEM_COUNT = 1
        private const val NUMBER_INDEX_STEP = 2
    }
}
