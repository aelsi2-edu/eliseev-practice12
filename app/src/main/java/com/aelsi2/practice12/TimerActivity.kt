package com.aelsi2.practice12

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.aelsi2.practice12.utils.debouncers.OnClickDebouncer

class TimerActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar
    private lateinit var cancelButton : View
    private lateinit var stopButton : View
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    private var timerType = TimerType.Wait
    enum class TimerType{
        Wait, Drive
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        timerType = when (intent.extras?.getBoolean("isDrive") ?: false) {
            true -> TimerType.Drive
            false -> TimerType.Wait
        }
        initViews()
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (timerType == TimerType.Drive) supportActionBar?.setTitle(R.string.drive_timer)
        else supportActionBar?.setTitle(R.string.wait_timer)
        cancelButton = findViewById(R.id.cancel_button)
        stopButton = findViewById(R.id.stop_button)
        if (timerType == TimerType.Drive) cancelButton.visibility = View.GONE
        cancelButton.setOnClickListener(onClickDebouncer)
        stopButton.setOnClickListener(onClickDebouncer)
    }

    override fun onClick(view: View?) {
        when (view) {
            cancelButton -> {
                finish()
            }
            stopButton -> {
                if (timerType == TimerType.Drive) {
                    AlertDialog.Builder(this)
                        .setMessage(R.string.thank_you)
                        .setPositiveButton(android.R.string.ok, null)
                        .setOnDismissListener{ finish() }.show()
                }
                else if (timerType == TimerType.Wait) {
                    finish()
                    val intent = Intent(this, TimerActivity::class.java)
                    intent.putExtra("isDrive", true)
                    startActivity(intent)
                }
            }
        }
    }

}