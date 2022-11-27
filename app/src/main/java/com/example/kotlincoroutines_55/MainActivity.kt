package com.example.kotlincoroutines_55

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main){
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                textView.text = answer
            }
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(5000L)
        return "This is the answer"
    }


}