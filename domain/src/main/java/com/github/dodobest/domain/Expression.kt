package com.github.dodobest.domain

class Expression {
    fun evaluate(input: String): List<String> {
        val inputString: String = eraseBlank(input)

        require(inputString != "") { "빈 공백문자를 입력했습니다." }

        return extractNumAndSignAll(inputString)
    }

    private fun eraseBlank(inputString: String): String {
        return inputString.replace(" ", "")
    }


    private fun extractNumAndSignAll(inputString: String): List<String> {
        val numAndSignArray: MutableList<String> = MutableList(0) { "" }
        val firstIndexOfNum: Array<Int> = arrayOf(NUM_IS_EMPTY)

        for (idx in inputString.indices) {
            numAndSignArray.addAll(splitNumAndSignOnce(inputString, idx, firstIndexOfNum))
        }
        numAndSignArray.add(inputString.slice(IntRange(firstIndexOfNum[0], inputString.length-1)))

        return numAndSignArray
    }

    private fun splitNumAndSignOnce(inputString: String, charIndex: Int, firstIndexOfNum: Array<Int>): List<String> {
        if (isNum(inputString[charIndex])) {
            updateFirstIndexOfNum(inputString, charIndex, firstIndexOfNum)
            return MutableList(0) { "" }
        }

        return checkArithmeticOperation(inputString, charIndex, firstIndexOfNum)
    }

    private fun updateFirstIndexOfNum(inputString: String, charIndex: Int, firstIndexOfNum: Array<Int>){
        if (firstIndexOfNum[0] != NUM_IS_EMPTY) {
            return
        }

        require(!checkNumStartWithZeroAndNotExactZero(inputString, charIndex)) { "0으로 시작하는 숫자는 지원하지 않습니다." }

        firstIndexOfNum[0] = charIndex
    }

    fun isNum(charVal: Char): Boolean {
        if (charVal.code in '0'.code..'9'.code ) {
            return true
        }
        return false
    }

    private fun checkNumStartWithZeroAndNotExactZero(inputString: String, charIndex: Int): Boolean {
        return inputString[charIndex] == '0' && charIndex < inputString.length - 1 && isNum(inputString[charIndex + 1])
    }

    private fun checkArithmeticOperation(inputString: String, charIndex: Int, firstIndexOfNum: Array<Int>): List<String> {
        val numAndSignArray: MutableList<String> = MutableList(0) { "" }

        checkInputIsNotCorrect(inputString, charIndex)

        if (isNegativeSignNotMinusSign(inputString[charIndex], firstIndexOfNum)) {
            firstIndexOfNum[0] = charIndex
            return MutableList(0) { "" }
        }

        if (isPositiveSign(inputString[charIndex], firstIndexOfNum)) {
            return MutableList(0) { "" }
        }

        numAndSignArray.add(inputString.slice(IntRange(firstIndexOfNum[0], charIndex-1)))
        numAndSignArray.add(inputString[charIndex].toString())

        firstIndexOfNum[0] = NUM_IS_EMPTY

        return numAndSignArray
    }

    private fun isPositiveSign(inputChar: Char, firstIndexOfNum: Array<Int>): Boolean {
        return Operation.convertToOperation(inputChar.toString()) == Operation.PLUS && firstIndexOfNum[0] == NUM_IS_EMPTY
    }

    private fun checkInputIsNotCorrect(inputString: String, charIndex: Int) {
//        require(Operation.isOperation(inputString[charIndex].toString())) { "사칙 연산 외 기호가 입력되었습니다." }
        Operation.convertToOperation(inputString[charIndex].toString())

        require(charIndex != inputString.length - 1) { "사칙 연산 뒤에 값이 오지 않았습니다." }

        require(!isDividedWithZero(inputString, charIndex)) { "0으로 나누는 값은 존재하지 않습니다." }

        throwErrorIfOperationIsConsecutive(inputString, charIndex)
    }

    private fun isNegativeSignNotMinusSign(inputChar: Char, firstIndexOfNum: Array<Int>): Boolean {
        return Operation.convertToOperation(inputChar.toString()) == Operation.MINUS && firstIndexOfNum[0] == NUM_IS_EMPTY
    }

    private fun isDividedWithZero(inputString: String, charIndex: Int): Boolean {
        return Operation.convertToOperation(inputString[charIndex].toString()) == Operation.DIVIDE && inputString[charIndex+1] == '0'
    }

    private fun throwErrorIfOperationIsConsecutive(inputString: String, charIndex: Int) {
        if (!Operation.isOperation(inputString[charIndex+1].toString())) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.MULTIPLY, Operation.PLUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.MULTIPLY, Operation.MINUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.DIVIDE, Operation.PLUS)) {
            return
        }

        if (checkOperationIsWhatExpect(inputString, charIndex, Operation.DIVIDE, Operation.MINUS)) {
            return
        }

        throw IllegalArgumentException("연산 기호가 연속으로 입력되었습니다.")
    }

    private fun checkOperationIsWhatExpect(inputString: String, charIndex: Int, firstOperation: Operation, secondOperation: Operation): Boolean {
        return inputString[charIndex].toString() == firstOperation.getName()
                && inputString[charIndex+1].toString() == secondOperation.getName()
    }

    companion object {
        const val NUM_IS_EMPTY = -1
    }
}