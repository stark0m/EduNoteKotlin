package com.example.edunotekotlin.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.MenuDrawable
import com.example.edunotekotlin.ui.main.MainFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), MenuDrawable {
    lateinit var drawler: DrawerLayout
    lateinit var navigationDrawlerView: NavigationView
    val fragment = MainFragment().newInstance()
    private var back_pressed: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawler = findViewById(R.id.drawer_layout)
        navigationDrawlerView = findViewById((R.id.navigation_view))


        if (savedInstanceState == null) {


            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

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
        navigationDrawlerView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_add -> {
                    fragment.presenter.addNote()
                    drawler.close()
                }
                else -> true
            }
            true
        }

    }

    override fun onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            // TODO:  super.onBackPressed()
        } else {
            Toast.makeText(baseContext, "Press once again to exit!", Toast.LENGTH_SHORT).show()
        }
        back_pressed = System.currentTimeMillis()
    }

}