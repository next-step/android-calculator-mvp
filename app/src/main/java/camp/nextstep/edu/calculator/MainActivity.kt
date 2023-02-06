package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.calculator.Calculator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator by lazy { Calculator() }
    private var input = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 수 입력
        binding.button0.setOnClickListener {
            input += "0"
            updateInput()
        }
        binding.button1.setOnClickListener {
            input += "1"
            updateInput()
        }
        binding.button2.setOnClickListener {
            input += "2"
            updateInput()
        }
        binding.button3.setOnClickListener {
            input += "3"
            updateInput()
        }
        binding.button4.setOnClickListener {
            input += "4"
            updateInput()
        }
        binding.button5.setOnClickListener {
            input += "5"
            updateInput()
        }
        binding.button6.setOnClickListener {
            input += "6"
            updateInput()
        }
        binding.button7.setOnClickListener {
            input += "7"
            updateInput()
        }
        binding.button8.setOnClickListener {
            input += "8"
            updateInput()
        }
        binding.button9.setOnClickListener {
            input += "9"
            updateInput()
        }

        // 사칙연산 기호 입력
        binding.buttonPlus.setOnClickListener {
            input += "+"
            updateInput()
        }
        binding.buttonMinus.setOnClickListener {
            input += "-"
            updateInput()
        }
        binding.buttonMultiply.setOnClickListener {
            input += "*"
            updateInput()
        }
        binding.buttonDivide.setOnClickListener {
            input += "/"
            updateInput()
        }

        // 입력창 초기화
        binding.buttonDelete.setOnClickListener { binding.textView.text = "" }

        // 결과값 출력
        binding.buttonEquals.setOnClickListener {
            val formula = binding.textView.text

            // 수식 계산
            val result = calculator.calculate(formula.toString())
            binding.textView.text = result.toString()
        }
    }

    private fun updateInput() {
        binding.textView.text = input
    }
}
