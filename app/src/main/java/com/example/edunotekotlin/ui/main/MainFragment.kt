package com.example.edunotekotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.main.mainpresenter.NoteMainPresenterImpl
import com.example.kotlineasynote.entities.OneNote


class MainFragment : Fragment(), ViewInterface {

    val presenter = NoteMainPresenterImpl(this)
    var recyclerViewAdapter = RecyclerViewAdapter()
    lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachFragment(this)

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
        recyclerView = view.findViewById(R.id.id_recycler_view)

        initRecycler()
        presenter.init()
        initCallBacks()

    }

    private fun initCallBacks() {
        recyclerViewAdapter.clickedNote = object :RecyclerViewAdapter.ClickedNote{
            override fun clicked(note: OneNote) {
                presenter.updateNote(note)
            }
        }
    }

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
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
        recyclerViewAdapter.data=list


    }

    override fun startLoading() {
//        TODO("показываем загрузочный экран")
    }

    override fun loaded() {
//        TODO("скрываем загрузочный экран")
    }
}