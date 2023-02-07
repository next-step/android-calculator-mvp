package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            binding.textView.text = binding.button0.text.toString()
        }
        binding.button1.setOnClickListener {
            binding.textView.text = binding.button1.text.toString()
        }
        binding.button2.setOnClickListener {
            binding.textView.text = binding.button2.text.toString()
        }
        binding.button3.setOnClickListener {
            binding.textView.text = binding.button3.text.toString()
        }
        binding.button4.setOnClickListener {
            binding.textView.text = binding.button4.text.toString()
        }
        binding.button5.setOnClickListener {
            binding.textView.text = binding.button5.text.toString()
        }
        binding.button6.setOnClickListener {
            binding.textView.text = binding.button6.text.toString()
        }
        binding.button7.setOnClickListener {
            binding.textView.text = binding.button7.text.toString()
        }
        binding.button8.setOnClickListener {
            binding.textView.text = binding.button8.text.toString()
        }
        binding.button9.setOnClickListener {
            binding.textView.text = binding.button9.text.toString()
        }


    }
}
