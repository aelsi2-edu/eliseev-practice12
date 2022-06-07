package com.aelsi2.practice12

import android.content.Intent
import android.os.Bundle
import android.view.View


class SettingsActivity : NavigationActivity(), View.OnClickListener {
    private lateinit var exitButton : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initNavViews()
        setupNavigation()
        initViews()
        supportActionBar?.title = getString(R.string.settings)
    }
    private fun initViews() {
        exitButton = findViewById(R.id.exit_button)
        exitButton.setOnClickListener(onClickDebouncer)
    }
    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    override fun onClick(view: View?) {
        when (view) {
            historyButton -> {
                hideDrawer()
                finish()
                showHistory()
            }
            settingsButton -> {
                hideDrawer()
            }
            exitButton -> {
                goToLogin()
            }
        }
    }
}