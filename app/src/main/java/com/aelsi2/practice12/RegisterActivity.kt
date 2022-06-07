package com.aelsi2.practice12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.aelsi2.practice12.utils.debouncers.OnClickDebouncer

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    private lateinit var loginButton : View
    private lateinit var usernameField : EditText
    private lateinit var emailField : EditText
    private lateinit var passwordField : EditText
    private lateinit var passwordConfirmField : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initViews()
    }
    private fun initViews() {
        loginButton = findViewById(R.id.sign_up_button)
        usernameField = findViewById<LoginTextField>(R.id.login_field).editText
        emailField = findViewById<LoginTextField>(R.id.email_field).editText
        passwordField = findViewById<LoginTextField>(R.id.password_field).editText
        passwordConfirmField = findViewById<LoginTextField>(R.id.confirm_password_field).editText
        loginButton.setOnClickListener(onClickDebouncer)
    }
    private fun allFieldsFilled() : Boolean {
        if (usernameField.text.isEmpty()) return false
        if (passwordField.text.isEmpty()) return false
        if (emailField.text.isEmpty()) return false
        if (passwordConfirmField.text.isEmpty()) return false
        return true
    }
    private fun passwordsMatch() : Boolean {
        if (passwordField.text.toString() == passwordConfirmField.text.toString()) return true
        return false
    }
    private fun goToMain() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onClick(view: View?) {
        if (view != loginButton) return
        if (!allFieldsFilled()) {
            Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }
        if (!passwordsMatch()) {
            Toast.makeText(this, getString(R.string.password_do_not_match), Toast.LENGTH_SHORT).show()
            return
        }
        goToMain()
    }
}