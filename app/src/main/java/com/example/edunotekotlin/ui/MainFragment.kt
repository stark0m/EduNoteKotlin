package com.example.edunotekotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.presenter.NoteMainPresenter
import com.example.edunotekotlin.ui.presenter.NoteMainPresenterImpl


class MainFragment : Fragment(),ViewInterface {

    val presenter = NoteMainPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun redraw() {
//        TODO("отрисовываем список ")
    }

    override fun startLoading() {
//        TODO("показываем загрузочный экран")
    }

    override fun loaded() {
//        TODO("скрываем загрузочный экран")
    }
}