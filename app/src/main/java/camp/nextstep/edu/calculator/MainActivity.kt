package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.calculatorlib.Calculator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var numAndOperator = ""
    private val calculate = Calculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener{addNumber('0')}
        binding.button1.setOnClickListener{addNumber('1')}
        binding.button2.setOnClickListener{addNumber('2')}
        binding.button3.setOnClickListener{addNumber('3')}
        binding.button4.setOnClickListener{addNumber('4')}
        binding.button5.setOnClickListener{addNumber('5')}
        binding.button6.setOnClickListener{addNumber('6')}
        binding.button7.setOnClickListener{addNumber('7')}
        binding.button8.setOnClickListener{addNumber('8')}
        binding.button9.setOnClickListener{addNumber('9')}

        binding.buttonPlus.setOnClickListener{addOperator('+')}
        binding.buttonMinus.setOnClickListener{addOperator('-')}
        binding.buttonMultiply.setOnClickListener{addOperator('*')}
        binding.buttonDivide.setOnClickListener{addOperator('/')}
        binding.buttonDelete.setOnClickListener{deleteLastStr()}
        binding.buttonEquals.setOnClickListener{calculate()}
    }

    private fun calculate() {
        if(isLastStrNum()) {
            showText(calculate.evaluate(numAndOperator).toString())
        } else {
            Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_SHORT).show()
        }
    }

    // 텍스트 화면 출력
    private fun showText(str: String) {
        binding.textView.text = str
    }
    // 숫자 추가
    private fun addNumber(str: Char) {
        if(numAndOperator.isEmpty()) {
            numAndOperator += str
            showText(numAndOperator)
            return
        }
        if(isLastStrNum()) {
            // 숫자 추가
            numAndOperator += str
        } else {
            // 스페이스 후 연산자 추가
            numAndOperator += " $str"
        }
        showText(numAndOperator)
    }

    // 연산자 추가
    // 직전 값이 연산자일 경우 교체한다.
    private fun addOperator(str: Char) {
        if(numAndOperator.isEmpty()) return
        if(isLastStrNum()) {
            // 스페이스 후 숫자
            numAndOperator += " $str"
        } else {
            // 연산자
            numAndOperator = numAndOperator.dropLast(1)
            numAndOperator += str
        }
        showText(numAndOperator)
    }

    // 직전 값이 숫자인가
    // return True: 숫자, False: 연산자
    // 직전 값이 없다면?
    private fun isLastStrNum(): Boolean {
        val lastIndex = numAndOperator.length - 1
        return numAndOperator[lastIndex].isDigit()
    }

    // 최근 항목 삭게
    private fun deleteLastStr() {
        if(numAndOperator.isEmpty()) return

        numAndOperator = numAndOperator.dropLast(1)
        numAndOperator = numAndOperator.trim()
        showText(numAndOperator)
    }
}
