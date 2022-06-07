package com.aelsi2.practice12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HistoryActivity : NavigationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        initNavViews()
        setupNavigation()
        supportActionBar?.title = getString(R.string.history)
    }
    override fun onClick(view: View?) {
        when (view) {
            historyButton -> {
                hideDrawer()
            }
            settingsButton -> {
                hideDrawer()
                finish()
                showSettings()
            }
        }
    }
}