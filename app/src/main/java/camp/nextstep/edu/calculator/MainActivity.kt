package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        initClickListener()
    }

    private fun initClickListener() {

        binding.button0.setOnClickListener { presenter.onButtonClicked("0") }
        binding.button1.setOnClickListener { presenter.onButtonClicked("1") }
        binding.button2.setOnClickListener { presenter.onButtonClicked("2") }
        binding.button3.setOnClickListener { presenter.onButtonClicked("3") }
        binding.button4.setOnClickListener { presenter.onButtonClicked("4") }
        binding.button5.setOnClickListener { presenter.onButtonClicked("5") }
        binding.button6.setOnClickListener { presenter.onButtonClicked("6") }
        binding.button7.setOnClickListener { presenter.onButtonClicked("7") }
        binding.button8.setOnClickListener { presenter.onButtonClicked("8") }
        binding.button9.setOnClickListener { presenter.onButtonClicked("9") }

        binding.buttonPlus.setOnClickListener { presenter.onButtonClicked("+") }
        binding.buttonMinus.setOnClickListener { presenter.onButtonClicked("-") }
        binding.buttonDivide.setOnClickListener { presenter.onButtonClicked("/") }
        binding.buttonMultiply.setOnClickListener { presenter.onButtonClicked("*") }

        binding.buttonDelete.setOnClickListener { presenter.onDeleteClicked() }
        binding.buttonMemory.setOnClickListener { presenter.onMemoryClicked() }
        binding.buttonEquals.setOnClickListener { presenter.onEqualsClicked() }
    }

    override fun showResult(expressions: String) {
        binding.textView.text = expressions
    }

    override fun showErrorToastMessage() {
        Toast.makeText(this@MainActivity, getString(R.string.incomplete_expression), Toast.LENGTH_SHORT).show()
    }
}
