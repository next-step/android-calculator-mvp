package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.domain.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val expression by lazy { Expression() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {
        binding.button0.setOnClickListener {
            expression.setOperands("0")
            binding.textView.text = expression.getOperands()
        }

        binding.button1.setOnClickListener {
            expression.setOperands("1")
            binding.textView.text = expression.getOperands()
        }

        binding.button2.setOnClickListener {
            expression.setOperands("2")
            binding.textView.text = expression.getOperands()
        }

        binding.button3.setOnClickListener {
            expression.setOperands("3")
            binding.textView.text = expression.getOperands()
        }

        binding.button4.setOnClickListener {
            expression.setOperands("4")
            binding.textView.text = expression.getOperands()
        }

        binding.button5.setOnClickListener {
            expression.setOperands("5")
            binding.textView.text = expression.getOperands()
        }

        binding.button6.setOnClickListener {
            expression.setOperands("6")
            binding.textView.text = expression.getOperands()
        }

        binding.button7.setOnClickListener {
            expression.setOperands("7")
            binding.textView.text = expression.getOperands()
        }

        binding.button8.setOnClickListener {
            expression.setOperands("8")
            binding.textView.text = expression.getOperands()
        }

        binding.button9.setOnClickListener {
            expression.setOperands("9")
            binding.textView.text = expression.getOperands()
        }

        binding.button0.setOnClickListener {
            expression.setOperands("0")
            binding.textView.text = expression.getOperands()
        }

        binding.buttonPlus.setOnClickListener {
            expression.setMethods("+")
            binding.textView.text = expression.getOperands()
        }

        binding.buttonMinus.setOnClickListener {
            expression.setMethods("-")
            binding.textView.text = expression.getOperands()
        }

        binding.buttonMultiply.setOnClickListener {
            expression.setMethods("*")
            binding.textView.text = expression.getOperands()
        }

        binding.buttonDivide.setOnClickListener {
            expression.setMethods("÷")
            binding.textView.text = expression.getOperands()
        }

        binding.buttonDelete.setOnClickListener {
            expression.removeOperands()
            binding.textView.text = expression.getOperands()
        }

        binding.buttonEquals.setOnClickListener {
            runCatching {
                expression.resultCalculate()
            }.onSuccess {
                binding.textView.text = expression.getOperands()
            }.onFailure {
                Toast.makeText(this@MainActivity, getString(R.string.incomplete_expression), Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonMemory.setOnClickListener {
            expression.resetMemory()
            binding.textView.text = expression.getOperands()
        }
    }
}
