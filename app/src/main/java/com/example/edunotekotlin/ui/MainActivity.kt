package com.example.edunotekotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    val fragment = MainFragment().newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState==null) {


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }}
}