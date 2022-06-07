package com.aelsi2.practice12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.aelsi2.practice12.utils.debouncers.OnClickDebouncer

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    private lateinit var loginButton : View
    private lateinit var registerButton : View
    private lateinit var usernameField : EditText
    private lateinit var passwordField : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
    }
    private fun initViews() {
        loginButton = findViewById(R.id.sign_in_button)
        usernameField = findViewById<LoginTextField>(R.id.login_field).editText
        passwordField = findViewById<LoginTextField>(R.id.password_field).editText
        registerButton = findViewById(R.id.create_account_text)
        loginButton.setOnClickListener(onClickDebouncer)
        registerButton.setOnClickListener(onClickDebouncer)
    }
    private fun allFieldsFilled() : Boolean {
        if (usernameField.text.isEmpty()) return false
        if (passwordField.text.isEmpty()) return false
        return true
    }
    private fun goToMain() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    override fun onClick(view: View?) {
        if (view == registerButton) {
            goToRegister()
            return
        }
        if (view != loginButton) return
        if (!allFieldsFilled()) {
            Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }
        goToMain()
    }
}