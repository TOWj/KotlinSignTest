package com.example.learnapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.learnapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var bindingClass: ActivityMainBinding

    private lateinit var launcherSignIn: ActivityResultLauncher<Intent>
    private lateinit var launcherSignUp: ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        launcherSignIn =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultIn: ActivityResult ->
                if (resultIn.resultCode == Activity.RESULT_OK) {
                    //If RESULT IS OK - entering
                    //Given fields Login & Password, and button "Sign In"
                    //If l & p is correct, transition to MainActivity
                    //Show fields and avatar and button "Exit"
                    bindingClass.imageView.setImageResource(R.drawable.one)
                    bindingClass.imageView.visibility = View.VISIBLE
                    bindingClass.signInBt.text = "Exit"
                    bindingClass.singUpBt.visibility = View.INVISIBLE
                    bindingClass.nameMainTv.text = resultIn.data?.getStringExtra("name").toString()
                    bindingClass.nameMainTv.visibility = View.VISIBLE
                    bindingClass.surnameMainTv.text = resultIn.data?.getStringExtra("surname").toString()
                    bindingClass.surnameMainTv.visibility = View.VISIBLE
                    bindingClass.ptrNameMainTv.text = resultIn.data?.getStringExtra("patron").toString()
                    bindingClass.ptrNameMainTv.visibility = View.VISIBLE
                } else {
                    //If incorrect, also transition to MainActivity
                    //Show text "Incorrect l & p" and both buttons "Sign"
                    bindingClass.incorrectTv.visibility = View.VISIBLE
                }
            }
        launcherSignUp =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultUp: ActivityResult ->
                if (resultUp.resultCode == Activity.RESULT_OK) {
                    bindingClass.imageView.setImageResource(R.drawable.one)
                    bindingClass.imageView.visibility = View.VISIBLE
                    bindingClass.signInBt.text = "Exit"
                    bindingClass.singUpBt.visibility = View.INVISIBLE
                    bindingClass.nameMainTv.text = resultUp.data?.getStringExtra("name").toString()
                    bindingClass.nameMainTv.visibility = View.VISIBLE
                    bindingClass.surnameMainTv.text = resultUp.data?.getStringExtra("surname").toString()
                    bindingClass.surnameMainTv.visibility = View.VISIBLE
                    bindingClass.ptrNameMainTv.text = resultUp.data?.getStringExtra("patron").toString()
                    bindingClass.ptrNameMainTv.visibility = View.VISIBLE
                    //If RESULT OK - registry
                    //Show all fields in RegistrationActivity
                    //Save changes is these fields
                    //Show button "Sign Up"
                    //After this, transition to MainActivity
                    //Show fields and avatar and button "Exit"
                }else{
                    bindingClass.incorrectTv.visibility = View.VISIBLE
                }
            }
    }


    fun onClickSignIn(view: View) {
        if (bindingClass.signInBt.text.toString() == "Exit") {
            resultBadAct()
        } else {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("keyword", "signIn")
            launcherSignIn.launch(intent)
        }
    }

    fun onClickSignUp(view: View) {
        if (bindingClass.signInBt.text.toString() == "Exit") {
            resultBadAct()
        } else {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("keyword", "signUp")
            launcherSignUp.launch(intent)
        }
    }

    private fun resultBadAct(){
        val intent = Intent(this, RegistrationActivity::class.java)
        bindingClass.imageView.visibility = View.INVISIBLE
        bindingClass.signInBt.text = getString(R.string.sign_in)
        bindingClass.singUpBt.visibility = View.VISIBLE
        bindingClass.nameMainTv.visibility = View.INVISIBLE
        bindingClass.surnameMainTv.visibility = View.INVISIBLE
        bindingClass.ptrNameMainTv.visibility = View.INVISIBLE
    }
}
