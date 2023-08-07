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
    private var _formulas: MutableList<String>

    init {
        val formula = formulaString?.trim()
        requireNotNull(formula) {
            "Input value is empty"
        }
        _formulas = formula.split(SEPERATOR).toMutableList()
    }

    fun addOperand(operand: String): String {
        val lastFormula = _formulas.lastOrNull()
        if(lastFormula.isNullOrEmpty()) {
            _formulas[0] = operand
        } else {
            if(lastFormula.isNum()) {
                _formulas[_formulas.lastIndex] = (getLastOperand(lastFormula) + operand)
            } else {
                _formulas.add(operand)
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
            _formulas.add(opcode)
        }
        return getFormulaString()
    }

    fun removeLast(): String {
        val lastFormula = _formulas.lastOrNull()
        if(lastFormula.isNullOrEmpty()) {
            return ""
        }
        if(lastFormula.isNum() && lastFormula.length > 1) {
            _formulas[_formulas.lastIndex] = lastFormula.dropLast(1)
        } else {
            _formulas.removeLast()
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
