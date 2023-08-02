package camp.nextstep.edu.calculator.domain

class Expression {

    constructor(formulaString: String?) {
        val formula = formulaString?.trim()
        require(!formula.isNullOrEmpty() && formula != "") {
            "Input value is empty"
        }
        formulas = formula.split(SEPERATOR).toMutableList()
        check(formulas.size % NUMBER_INDEX_STEP == EXTRA_ITEM_COUNT) {
            "완성되지 않은 수식입니다"
        }
        formulas.withIndex()
            .filter { (index, formula) -> index % NUMBER_INDEX_STEP == 0}
            .map { (index, formula) ->
                require(formula.isNum()) {
                    "Parameter must be Number"
                }
            }
    }

    val formulas: List<String>

    private fun String.isNum(): Boolean {
        this.toIntOrNull() ?: return false
        return true
    }

    companion object {
        private const val SEPERATOR = " "
        private const val EXTRA_ITEM_COUNT = 1
        private const val NUMBER_INDEX_STEP = 2
    }
}
