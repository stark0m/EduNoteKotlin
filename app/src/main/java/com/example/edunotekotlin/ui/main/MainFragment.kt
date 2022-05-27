package com.example.edunotekotlin.ui.main

import android.os.Bundle
import android.os.FileUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.MenuDrawable
import com.example.edunotekotlin.ui.main.mainpresenter.NoteMainPresenterImpl
import com.example.kotlineasynote.entities.OneNote
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment(), ViewInterface {

    val presenter: NoteMainPresenterImpl by lazy { NoteMainPresenterImpl(this) }
    lateinit var progressbar: ProgressBar
    lateinit var toolbar: MaterialToolbar
    var recyclerViewAdapter = RecyclerViewAdapter()
    lateinit var recyclerView: RecyclerView
    lateinit var floatButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachFragment(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            recyclerView = findViewById(R.id.id_recycler_view)
            floatButton = findViewById(R.id.floating_action_button)
            progressbar = findViewById(R.id.progress)
            toolbar = findViewById(R.id.fragment_main_toolbar)
        }
        if (requireActivity() is MenuDrawable) {
            (requireActivity() as MenuDrawable).setAppToolbar(toolbar)
        }


        initRecycler()
        presenter.init()
        initCallBacks()
        initListeners()


    }

    private fun initListeners() {
        floatButton.setOnClickListener() {
            presenter.addNote()
        }
    }

    private fun initCallBacks() {
        recyclerViewAdapter.clickedNote = object : RecyclerViewAdapter.ClickedNote {
            override fun clicked(note: OneNote) {
                presenter.updateNote(note)
            }
        }


    }

    private fun initRecycler() {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = recyclerViewAdapter
    }


    override fun onDetach() {
        super.onDetach()
        presenter.detouchFragment()
    }

    override fun redraw() {

        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun redrawRecyclerInPosition(position: Int) {

        recyclerViewAdapter.notifyItemChanged(position)
    }

    override fun writeNoteListToData(list: MutableList<OneNote>) {
        recyclerViewAdapter.data = list


    }

    override fun startLoading() {
        progressbar.visibility = View.VISIBLE

    }

    override fun loaded() {
        progressbar.visibility = View.GONE

    }

    override fun addNote(newNote: OneNote) {
        recyclerViewAdapter.data.add(0, newNote)
        recyclerViewAdapter.notifyItemChanged(0)
    }

    companion object

    fun newInstance(): MainFragment {
        val instance: MainFragment by lazy { MainFragment() }
        return instance
    }


}


