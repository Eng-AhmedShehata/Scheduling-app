package com.ashehata.schedulingapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ashehata.schedulingapp.R
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFun: (Int) -> (Int) = { a -> a*2 }

        val clicked = { a: Int, b: Int ->


        }


        val noo = fun (a: Int, b:Int) {

        }
        View(this).setOnClickListener {
            clicked(12,5)
        }
        val res = mFun(5)

        helloHieher {
            a -> return@helloHieher a*2
        }

        passMe{
            a, b -> println(a+b)
        }

    }

    fun passMe(abc: (Int, Float) -> Unit) {

        abc(5, 12f)
    }

    fun add(a: Int): Int {
        return 12
    }

    fun returnAFun() : (Int) -> Int {
        return  ::add
    }

    fun helloHieher(block: (Int) -> Int) {
        val aa = block(12)
    }

}
