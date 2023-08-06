package camp.nextstep.edu.calculator.domain

class Expression(formulaString: String? = "") {
    val formulas: List<String>
        get() {
            require(_formulas.isNotEmpty()) {
                "Input value is empty"
            }
            check(_formulas.size % NUMBER_INDEX_STEP == EXTRA_ITEM_COUNT) {
                "완성되지 않은 수식입니다"
            }
            _formulas.withIndex()
                .filter { (index, formula) -> index % NUMBER_INDEX_STEP == 0}
                .map { (index, formula) ->
                    require(formula.isNum()) {
                        "Parameter must be Number"
                    }
                }
            return _formulas
        }
    private var _formulas: List<String>

    init {
        val formula = formulaString?.trim()
        require(formula != null) {
            "Input value is empty"
        }
        _formulas = formula.split(SEPERATOR)
    }

    fun addOperand(operand: String): String {
        val lastFormula = _formulas.lastOrNull()
        _formulas = if(lastFormula.isNullOrEmpty()) {
            listOf(operand)
        } else {
            if(lastFormula.isNum()) {
                _formulas.dropLast(1) + (getLastOperand(lastFormula) + operand)
            } else {
                _formulas + operand
            }
        }
        return getFormulaString()
    }

    private fun getLastOperand(lastFormula: String): String = (if (lastFormula == "0") "" else lastFormula)

    fun addOpcode(opcode: String): String {
        val lastFormula = _formulas.lastOrNull()
        check(!lastFormula.isNullOrEmpty()) {
            "완성되지 않은 수식입니다"
        }
        if(lastFormula.isNum()) {
            _formulas = _formulas + opcode
        }
        return getFormulaString()
    }

    fun removeLast(): String {
        val lastFormula = _formulas.lastOrNull()
        if(lastFormula.isNullOrEmpty()) {
            return ""
        }
        _formulas = if(lastFormula.isNum() && lastFormula.length > 1) {
            _formulas.dropLast(1) + lastFormula.dropLast(1)
        } else {
            _formulas.dropLast(1)
        }
        return getFormulaString()
    }

    fun getFormulaString(): String {
        return _formulas.joinToString(" ")
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
