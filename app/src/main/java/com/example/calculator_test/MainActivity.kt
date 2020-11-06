package com.example.calculator_test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var value1: Int = 0
    var value2: Int = 0
    var resultCalc: Int = 0
    var signEntered: Boolean = false
    var sign: String = ""
    var memorySavior: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = "0"

        negative_to_positive.setOnClickListener{signChange()}

        memory_clear.setOnClickListener{memoryClear()}
        memory_minus.setOnClickListener{memoryMinus()}
        memory_plus.setOnClickListener{memoryPlus()}
        memory_save.setOnClickListener{memorySave()}
        memory_recal.setOnClickListener{memoryRecall()}

        all_clear.setOnClickListener{allClear()}
        clear.setOnClickListener{clearC()}
        backspace.setOnClickListener{backspaceClear()}

        button_1.setOnClickListener{ writeInField("1") }
        button_2.setOnClickListener{ writeInField("2") }
        button_3.setOnClickListener{ writeInField("3") }
        button_4.setOnClickListener{ writeInField("4") }
        button_5.setOnClickListener{ writeInField("5") }
        button_6.setOnClickListener{ writeInField("6") }
        button_7.setOnClickListener{ writeInField("7") }
        button_8.setOnClickListener{ writeInField("8") }
        button_9.setOnClickListener{ writeInField("9") }
        button_0.setOnClickListener{ writeInField("0") }

        addition.setOnClickListener{
            sign = "+"
            textView.text = "0"
            signEntered = true
        }
        subtraction.setOnClickListener{
            sign = "-"
            textView.text = "0"
            signEntered = true
        }
        multiplication.setOnClickListener{
            sign = "*"
            textView.text = "0"
            signEntered = true
        }
        divide.setOnClickListener{
            sign = "/"
            textView.text = "0"
            signEntered = true
        }

        calculate_button.setOnClickListener{ calculate() }
    }

    @SuppressLint("SetTextI18n")
    private fun writeInField(id: String) {
        if (textView.text == "0"){
            textView.text = id
        }
        else if (textView.text != "0"){
            textView.text = textView.text.toString() + id
        }

        if (!signEntered) value1 = textView.text.toString().toInt()
        else value2 = textView.text.toString().toInt()
    }

    private fun calculate(){
        if (signEntered){
            if (sign == "+") resultCalc = value1 + value2
            if (sign == "*") resultCalc = value1 * value2
            if (sign == "/") resultCalc = value1 / value2
            if (sign == "-") resultCalc = value1 - value2

            textView.text = resultCalc.toString()
            sign = ""
            value1 = resultCalc
            value2 = 0
            signEntered = false
        }
    }

    private fun signChange(){
        textView.text = (textView.text.toString().toInt() * (-1)).toString()

        if (signEntered) value2 = textView.text.toString().toInt()
        else value1 = textView.text.toString().toInt()
    }

    private fun allClear(){
        textView.text = "0"
        value1 = 0
        value2 = 0
        signEntered = false
        sign = ""
        resultCalc = 0
        memorySavior = 0
    }

    private fun clearC() {
        textView.text = "0"

        if (signEntered) value2 = 0
        else value1 = 0
    }

    private fun backspaceClear(){
        textView.text = textView.text.dropLast(1)
        if (textView.text.isEmpty() || textView.text.endsWith("-")) textView.text = "0"

        if (signEntered) value2 = textView.text.toString().toInt()
        else value1 = textView.text.toString().toInt()
    }

    private fun memoryClear(){
        memorySavior = 0
    }

    private fun memorySave(){
        memorySavior = if (signEntered) value2 else value1
    }

    private fun memoryPlus(){
        memorySavior += if (signEntered) value2 else value1
    }

    private fun memoryMinus(){
        memorySavior -= if (signEntered) value2 else value1
    }

    private fun memoryRecall(){
        textView.text = memorySavior.toString()

        if (signEntered) value2 = memorySavior
        else value1 = memorySavior
    }
}

