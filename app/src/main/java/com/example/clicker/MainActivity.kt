package com.example.clicker

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    lateinit var tv_time: TextView
    lateinit var tv_clicks: TextView
    lateinit var tv_last_score: TextView

    lateinit var b_start: Button
    lateinit var b_click: Button
    lateinit var b_exit: Button

    var currentTime = 10
    var currentClicks = 0
    var last_score = 0


    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_time = findViewById(R.id.tv_time)
        tv_clicks = findViewById(R.id.tv_clicks)
        tv_last_score = findViewById(R.id.tv_last_score)

        b_start = findViewById(R.id.b_start)
        b_click = findViewById(R.id.b_click)
        b_exit = findViewById(R.id.b_exit)


        b_click.isEnabled = false

        b_start.setOnClickListener {
            currentTime = 10
            currentClicks = 0

            tv_time.text = "Time: $currentTime"
            tv_clicks.text = "Clicks: $currentClicks"

            b_start.isEnabled = false
            b_click.isEnabled = true

            timer.start()
        }

        b_click.setOnClickListener {
            val random = Random
            val color =
                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            b_click.setBackgroundColor(color)
            currentClicks++;
            tv_clicks.text = "Clicks: $currentClicks"
        }

        b_exit.setOnClickListener {
            finish()
        }

        timer = object :
            CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime--
                val time = currentTime + 1
                tv_time.text = "Time: $time"
            }

            override fun onFinish() {
                tv_time.text = "Time: 0"
                last_score = currentClicks
                tv_last_score.text = "Last score: $last_score"

                b_start.isEnabled = true
                b_click.isEnabled = false
            }
        }
    }
}