package com.example.agro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.agro.R

class CalActivity : AppCompatActivity() {

    private lateinit var numbersField: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)

        numbersField = findViewById(R.id.numbers_field)
        resultTextView = findViewById(R.id.result_textview)

        findViewById<Button>(R.id.button_add).setOnClickListener {
            performOperation('+')
        }
        findViewById<Button>(R.id.button_subtract).setOnClickListener {
            performOperation('-')
        }
        findViewById<Button>(R.id.button_multiply).setOnClickListener {
            performOperation('*')
        }
        findViewById<Button>(R.id.button_divide).setOnClickListener {
            performOperation('/')
        }

        findViewById<Button>(R.id.button_calculate).setOnClickListener {
            val input = numbersField.text.toString()
            if (input.isNotEmpty()) {
                val numbers = input.split(Regex("(?<=[-+*/])|(?=[-+*/])"))
                var result = numbers[0].toDouble()
                for (i in 1 until numbers.size step 2) {
                    val operator = numbers[i]
                    val operand = numbers[i + 1].toDouble()
                    result = performOperation(result, operand, operator[0])
                }
                resultTextView.text = result.toString()
            }
        }
    }

    private fun performOperation(operator: Char) {
        val input = numbersField.text.toString()
        if (input.isNotEmpty()) {
            numbersField.setText("$input$operator")
            numbersField.setSelection(numbersField.text.length)
        }
    }

    private fun performOperation(a: Double, b: Double, operator: Char): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}
