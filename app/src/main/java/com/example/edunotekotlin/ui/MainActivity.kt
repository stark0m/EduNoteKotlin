package com.example.edunotekotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.MenuDrawable
import com.example.edunotekotlin.ui.main.MainFragment
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity(),MenuDrawable {
    lateinit var drawler: DrawerLayout
    val fragment = MainFragment().newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawler = findViewById(R.id.drawer_layout)



        if (savedInstanceState==null) {


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }}

    override fun setAppToolbar(toolbar: MaterialToolbar) {
        var toggle = ActionBarDrawerToggle(
            this,
            drawler,
            toolbar,
            R.string.open_rawler,
            R.string.close_drawler
        )
        drawler.addDrawerListener(toggle)
        toggle.syncState()
    }
}