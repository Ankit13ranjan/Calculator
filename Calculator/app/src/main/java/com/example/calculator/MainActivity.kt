package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean =false
    var lastdot : Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric=true


    }

    fun onClear(view: View) {
        tvInput.text="";
        lastdot=false
        lastNumeric=false
    }

    fun onDecimal(view: View) {
        if(lastNumeric && !lastdot)
        {
            tvInput.append(".")
            lastdot=true
            lastNumeric=false

        }

    }

    fun onEqual(view: View)
    {
        if(lastNumeric)
        {
            var prefix=""
            var tvValue = tvInput.text.toString()
            try {
                if(tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("+"))
                {
                    val split = tvValue.split("+")

                    var a = split[0];
                    var b = split[1];

                    if(!prefix.isEmpty())
                    {
                        a = prefix + a;
                    }

                    tvInput.text =removedigitfromlast((a.toDouble() + b.toDouble()).toString())
                }
                else if(tvValue.contains("-"))
                {
                    val split = tvValue.split("-")

                    var a = split[0];
                    var b = split[1];

                    if(!prefix.isEmpty())
                    {
                        a = prefix + a;
                    }

                    tvInput.text =removedigitfromlast((a.toDouble() - b.toDouble()).toString())
                }
                else if(tvValue.contains("*"))
                {
                    val split = tvValue.split("*")

                    var a = split[0];
                    var b = split[1];

                    if(!prefix.isEmpty())
                    {
                        a = prefix + a;
                    }

                    tvInput.text =removedigitfromlast((a.toDouble() * b.toDouble()).toString())
                }
                else if(tvValue.contains("/"))
                {
                    val split = tvValue.split("/")

                    var a = split[0];
                    var b = split[1];

                    if(!prefix.isEmpty())
                    {
                        a = prefix + a;
                    }

                    tvInput.text =removedigitfromlast((a.toDouble() / b.toDouble()).toString())
                }

            }
            catch (e : ArithmeticException)
            {
                e.printStackTrace()
            }
        }

    }

    private fun removedigitfromlast(result : String) : String
    {
        var value = result
        if(value.contains("0"))
        {
            value = value.substring(0,result.length-2)
        }
        return value;
    }
    fun onOperator(view: View)
    {
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString()))
        {
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastdot=false
        }

    }
    private fun isOperatorAdded(value : String) : Boolean
    {
        return  if(value.startsWith("-"))
        {
            false
        }
        else
        {
            value.contains("+") || value.contains("-") ||
                    value.contains("*") || value.contains("/")
        }
    }

    fun onErase(view: View)
    {
        var value = tvInput.text.toString()
        value = value.substring(0,value.length-1)
        tvInput.text = value
    }


}