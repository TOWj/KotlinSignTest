package com.example.learnapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.learnapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var surname: String = "empty"
    private var patron: String = "empty"
    private var imageId: Int = 0


    private lateinit var bindingClass: ActivityMainBinding

    private lateinit var launcherSignIn: ActivityResultLauncher<Intent>
    private lateinit var launcherSignUp: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        launcherSignIn =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultIn: ActivityResult ->
                if (resultIn.resultCode == RESULT_OK) {

                    if (resultIn.data?.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_IN_STATE) {
                        val l = resultIn.data?.getStringExtra(Constance.LOGIN)
                        val p = resultIn.data?.getStringExtra(Constance.PASSWORD)

                        if (login == l && password == p) {

                            bindingClass.mainAvatarIv.visibility = View.VISIBLE
                            bindingClass.mainAvatarIv.setImageResource(imageId)
                            var textInfo = "$name $surname $patron"
                            bindingClass.infoTv.text = textInfo
                            bindingClass.signUpBt.visibility = View.INVISIBLE
                            bindingClass.exitSignInBt.text = Constance.EXIT

                        } else {

                            bindingClass.infoTv.text = Constance.FAIL_ENTER
                            bindingClass.mainAvatarIv.setImageResource(R.drawable.figa)

                        }
                    }

                }

            }
        launcherSignUp =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultUp: ActivityResult ->
                if (resultUp.resultCode == RESULT_OK) {

                    login = resultUp.data?.getStringExtra(Constance.LOGIN)!!
                    password = resultUp.data?.getStringExtra(Constance.PASSWORD)!!
                    name = resultUp.data?.getStringExtra(Constance.NAME)!!
                    surname = resultUp.data?.getStringExtra(Constance.SURNAME)!!
                    patron = resultUp.data?.getStringExtra(Constance.PATRON)!!
                    imageId = resultUp.data?.getIntExtra(Constance.AVATAR_ID, 0)!!

                    bindingClass.mainAvatarIv.visibility = View.VISIBLE

                    bindingClass.mainAvatarIv.setImageResource(imageId)
                    var textInfo = "$name $surname $patron"
                    bindingClass.infoTv.text = textInfo
                    bindingClass.signUpBt.visibility = View.INVISIBLE
                    bindingClass.exitSignInBt.text = Constance.EXIT


                } else {
                    bindingClass.infoTv.text = "fuck"

                }
            }
    }


    fun onClickSignIn(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
        launcherSignIn.launch(intent)

    }

    fun onClickSignUp(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        launcherSignUp.launch(intent)

    }

}
