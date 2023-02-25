package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // presenter 초기화
        presenter = CalculatorPresenter(this)

        // 수 입력
        binding.button0.setOnClickListener { presenter.addInput("0") }
        binding.button1.setOnClickListener { presenter.addInput("1") }
        binding.button2.setOnClickListener { presenter.addInput("2") }
        binding.button3.setOnClickListener { presenter.addInput("3") }
        binding.button4.setOnClickListener { presenter.addInput("4") }
        binding.button5.setOnClickListener { presenter.addInput("5") }
        binding.button6.setOnClickListener { presenter.addInput("6") }
        binding.button7.setOnClickListener { presenter.addInput("7") }
        binding.button8.setOnClickListener { presenter.addInput("8") }
        binding.button9.setOnClickListener { presenter.addInput("9") }

        // 사칙연산 기호 입력
        binding.buttonPlus.setOnClickListener { presenter.addInput(PLUS.operator) }
        binding.buttonMinus.setOnClickListener { presenter.addInput(MINUS.operator) }
        binding.buttonMultiply.setOnClickListener { presenter.addInput(MULTIPLY.operator) }
        binding.buttonDivide.setOnClickListener { presenter.addInput(DIVIDE.operator) }

        // 입력된 문자 1개 지우기
        binding.buttonDelete.setOnClickListener { presenter.removeLastInput() }

        // 결과값 출력
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
    }

    override fun showInput(input: String) {
        binding.textView.text = input
    }

    override fun showResult(result: Int) {
        binding.textView.text = result.toString()
    }
}
