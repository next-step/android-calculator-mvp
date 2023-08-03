package camp.nextstep.edu.calculator.domain

class Expression {

    var formulas = listOf<String>()

    constructor()

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

    fun addOperand(operand: String): String {
        val lastFormula = formulas.lastOrNull()
        formulas = if(lastFormula == null) {
            formulas + operand
        } else {
            if(lastFormula.isNum()) {
                formulas.dropLast(1) + (lastFormula + operand)
            } else {
                formulas + operand
            }
        }
        return getFormulaString()
    }

    fun addOpCode(opCode: String): String {
        val lastFormula = formulas.lastOrNull()
        if(lastFormula != null) {
            if(lastFormula.isNum()) {
                formulas = formulas + opCode
            }
        }
        return getFormulaString()
    }

    fun removeLast(): String {
        if(formulas.isNotEmpty()) {
            formulas = formulas.dropLast(1)
        }
        return getFormulaString()
    }

    fun getFormulaString(): String {
        return formulas.joinToString(" ")
    }

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
