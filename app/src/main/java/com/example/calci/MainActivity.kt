package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        num0.setOnClickListener { appendOnClick(true, "0") }
        num1.setOnClickListener { appendOnClick(true, "1") }
        num2.setOnClickListener { appendOnClick(true, "2") }
        num3.setOnClickListener { appendOnClick(true, "3") }
        num4.setOnClickListener { appendOnClick(true, "4") }
        num5.setOnClickListener { appendOnClick(true, "5") }
        num6.setOnClickListener { appendOnClick(true, "6") }
        num7.setOnClickListener { appendOnClick(true, "7") }
        num8.setOnClickListener { appendOnClick(true, "8") }
        num9.setOnClickListener { appendOnClick(true, "9") }
        dot.setOnClickListener { appendOnClick(true, ".") }


        add.setOnClickListener { appendOnClick(false, "+") }
        sub.setOnClickListener { appendOnClick(false, "-") }
        multiply.setOnClickListener { appendOnClick(false, "*") }
        divide.setOnClickListener { appendOnClick(false, "/") }
        leftB.setOnClickListener { appendOnClick(false, "(") }
        rightB.setOnClickListener { appendOnClick(false, ")") }


        clear.setOnClickListener {
            clear()
        }

        equalTo.setOnClickListener {
            calculate()
        }

        backSpace.setOnClickListener {
            backSpace()
        }

    }


    private fun appendOnClick(clear: Boolean, string: String) {

        if (clear) {
            tvOutp.text = ""
            tvInp.append(string)
        } else {
            tvInp.append(tvOutp.text)
            tvInp.append(string)
            tvOutp.text = ""
        }
    }

    private fun clear() {
        tvInp.text = ""
        tvOutp.text = ""

    }

    private fun calculate() {

        try {

            val input = ExpressionBuilder(tvInp.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()){
                tvOutp.text = longOutput.toString()
            }else{
                tvOutp.text = output.toString()
            }

        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }

    private fun backSpace() {

        val string = tvInp.text.toString()
        if (string.isNotEmpty()) {
            tvInp.text = string.substring(0,string.length-1)
        }
        tvOutp.text = ""
    }
}