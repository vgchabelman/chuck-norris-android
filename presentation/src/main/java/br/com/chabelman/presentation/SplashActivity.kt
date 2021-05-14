package br.com.chabelman.presentation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val countDownTimer = object : CountDownTimer(1500, 500) {
        override fun onTick(millisUntilFinished: Long) { //noop
        }

        override fun onFinish() {
            if (isFinishing) return
            val intent = Intent(baseContext, BaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countDownTimer.start()
    }
}