package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding

    private fun initBinding() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }


    private fun numberButton() {
        supportActionBar?.hide()
        mBinding.apply {
            button0 addingText ("0")
            button1 addingText ("1")
            button2 addingText ("2")
            button3 addingText ("3")
            button4 addingText ("4")
            button5 addingText ("5")
            button6 addingText ("6")
            button7 addingText ("7")
            button8 addingText ("8")
            button9 addingText ("9")
            buttonDiv addingText ("/")
            buttonPlus addingText ("+")
            buttonMulti addingText ("*")
            buttonMinus addingText ("-")
            buttonPoint addingText (".")
            buttonParentheses addingText ("(")
            buttonParentheses.setOnLongClickListener {
                mBinding.inputText.append(")")
                true
            }


        }
    }

    private fun buttonAc() {
        mBinding.buttonClear.setOnClickListener {
            mBinding.inputText.setText("")
            mBinding.resultText.setText("")
        }
    }

    private fun buttonDelete() {
        mBinding.buttonDelete.setOnClickListener {
            val length = mBinding.inputText.text.length
            if (length > 0) mBinding.inputText.text =
                mBinding.inputText.text.subSequence(0, length - 1)
        }

    }

    private fun resultButton() {
        mBinding.buttonResult.setOnClickListener {
            try {
                val expression = ExpressionBuilder(mBinding.inputText.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    mBinding.resultText.text = "= ${longResult.toString()}"
                } else {
                    mBinding.resultText.text = "= ${result.toString()}"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error Received", Toast.LENGTH_LONG)
            }
        }


    }


    private infix fun View.addingText(string: String) {
        setOnClickListener {
            mBinding.inputText.append(string)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Calculator"
        initBinding()
        numberButton()
        resultButton()
        buttonAc()
        buttonDelete()

    }
}









