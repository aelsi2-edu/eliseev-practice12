package com.aelsi2.practice12

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.aelsi2.practice12.utils.debouncers.OnClickDebouncer

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var historyButton : View
    private lateinit var settingsButton : View
    private lateinit var menuButton : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        historyButton = findViewById(R.id.drawer_history_button)
        settingsButton = findViewById(R.id.drawer_settings_button)
        menuButton = findViewById(R.id.menu_button)
        historyButton.setOnClickListener(onClickDebouncer)
        settingsButton.setOnClickListener(onClickDebouncer)
        menuButton.setOnClickListener(onClickDebouncer)
    }
    private fun showDrawer() {
        drawerLayout.openDrawer(GravityCompat.START, true)
    }
    private fun hideDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun showSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
    private fun showHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        when (view) {
            historyButton -> {
                hideDrawer()
                showHistory()
            }
            settingsButton -> {
                hideDrawer()
                showSettings()
            }
            menuButton -> {
                showDrawer()
            }
        }
    }

}