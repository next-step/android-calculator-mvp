package camp.nextstep.edu.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    override var presenter: Contract.Presenter = Presenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { presenter.addNumber('0') }
        binding.button1.setOnClickListener { presenter.addNumber('1') }
        binding.button2.setOnClickListener { presenter.addNumber('2') }
        binding.button3.setOnClickListener { presenter.addNumber('3') }
        binding.button4.setOnClickListener { presenter.addNumber('4') }
        binding.button5.setOnClickListener { presenter.addNumber('5') }
        binding.button6.setOnClickListener { presenter.addNumber('6') }
        binding.button7.setOnClickListener { presenter.addNumber('7') }
        binding.button8.setOnClickListener { presenter.addNumber('8') }
        binding.button9.setOnClickListener { presenter.addNumber('9') }

        binding.buttonPlus.setOnClickListener { presenter.addOperator('+') }
        binding.buttonMinus.setOnClickListener { presenter.addOperator('-') }
        binding.buttonMultiply.setOnClickListener { presenter.addOperator('*') }
        binding.buttonDivide.setOnClickListener { presenter.addOperator('/') }
        binding.buttonDelete.setOnClickListener { presenter.deleteLastStr() }
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
    }

    override fun showFormula(formula: String) {
        binding.textView.text = formula
    }

    @SuppressLint("SetTextI18n")
    override fun showResult(result: String) {
        binding.textView.text = "${binding.textView.text}\n= $result"
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
