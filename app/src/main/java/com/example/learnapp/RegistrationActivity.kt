package com.example.learnapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.learnapp.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private var signState = "empty"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!

        if (signState == Constance.SIGN_IN_STATE) {
            binding.nameEt.visibility = View.GONE
            binding.surnameEt.visibility = View.GONE
            binding.patronicNameEt.visibility = View.GONE
            binding.avatarBt.visibility = View.INVISIBLE
        }

    }

    fun onClickDone(view: View) {

        if (signState == Constance.SIGN_UP_STATE) {
            val intent = Intent()
            intent.putExtra(Constance.LOGIN, binding.loginEt.text.toString())
            intent.putExtra(Constance.PASSWORD, binding.passwordEt.text.toString())
            intent.putExtra(Constance.NAME, binding.nameEt.text.toString())
            intent.putExtra(Constance.SURNAME, binding.surnameEt.text.toString())
            intent.putExtra(Constance.PATRON, binding.patronicNameEt.text.toString())
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            if (binding.avatarRegIv.isVisible) intent.putExtra(Constance.AVATAR_ID, R.drawable.one)
            setResult(RESULT_OK, intent)
            finish()
        } else if (signState == Constance.SIGN_IN_STATE) {
            intent.putExtra(Constance.LOGIN, binding.loginEt.text.toString())
            intent.putExtra(Constance.PASSWORD, binding.passwordEt.text.toString())
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun onClickAvatar(view: View) {

        binding.avatarRegIv.visibility = View.VISIBLE

    }


}