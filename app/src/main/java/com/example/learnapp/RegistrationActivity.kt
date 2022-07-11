package com.example.learnapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnapp.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var message: String


    private var name: String = "default"
    private var surname: String = "default"
    private var patron: String = "default"
    private lateinit var login: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        message = intent.getStringExtra("keyword").toString()

        if (message == "signIn") {
            //if entering
            binding.registrationBt.text = getString(R.string.sign_in)
            binding.nameEt.visibility = View.INVISIBLE
            binding.surnameEt.visibility = View.INVISIBLE
            binding.patronicNameEt.visibility = View.INVISIBLE
        } else if (message == "signUp") {
            //if registry
            binding.registrationBt.text = getString(R.string.sign_up)
        }

    }

    fun onClickButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        if (message == "signIn") {
            //if entering

            if (login == binding.loginEt.text.toString() && password == binding.passwordEt.text.toString()) {
                intent.putExtra("name", name)
                intent.putExtra("surname", surname)
                intent.putExtra("patron", patron)
                setResult(RESULT_OK, intent)
            } else {
                setResult(RESULT_CANCELED, intent)
            }
        } else if (message == "signUp") {
            //if registry
            name = binding.nameEt.text.toString()
            surname = binding.surnameEt.text.toString()
            patron = binding.patronicNameEt.text.toString()
            login = binding.loginEt.text.toString()
            password = binding.passwordEt.text.toString()
            setResult(RESULT_OK, intent)
            intent.putExtra("name", name)
            intent.putExtra("surname", surname)
            intent.putExtra("patron", patron)
            intent.putExtra("login", login)
            intent.putExtra("password", password)
            finish()
        } else {
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        finish()
    }
}