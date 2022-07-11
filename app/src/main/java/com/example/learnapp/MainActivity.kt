package com.example.learnapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var bindingClass: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


    }
}
