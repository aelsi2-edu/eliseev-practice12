package com.aelsi2.practice12

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.aelsi2.practice12.utils.debouncers.OnClickDebouncer

abstract class NavigationActivity(debouncerThreshold : Long = 500) : AppCompatActivity(), View.OnClickListener {
    protected val onClickDebouncer = OnClickDebouncer(this, debouncerThreshold)
    protected lateinit var toolbar : androidx.appcompat.widget.Toolbar
    protected lateinit var toggle : ActionBarDrawerToggle
    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var historyButton : View
    protected lateinit var settingsButton : View

    protected fun initNavViews() {
        historyButton = findViewById(R.id.drawer_history_button)
        settingsButton = findViewById(R.id.drawer_settings_button)
        historyButton.setOnClickListener(onClickDebouncer)
        settingsButton.setOnClickListener(onClickDebouncer)
    }
    protected fun setupNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        drawerLayout.addDrawerListener(toggle)
    }
    protected fun showDrawer() {
        drawerLayout.openDrawer(GravityCompat.START, true)
    }
    protected fun hideDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }
    protected fun showSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
    protected fun showHistory() {
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
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                showDrawer()
                return true
            }
        }
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }
}