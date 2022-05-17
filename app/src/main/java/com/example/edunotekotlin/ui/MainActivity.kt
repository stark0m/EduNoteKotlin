package com.example.edunotekotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edunotekotlin.R

class MainActivity : AppCompatActivity() {
    lateinit var fragment: MainFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState==null) {
            fragment = MainFragment()
                    }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}