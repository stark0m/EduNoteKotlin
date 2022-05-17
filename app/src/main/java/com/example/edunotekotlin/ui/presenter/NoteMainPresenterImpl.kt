package com.example.edunotekotlin.ui.presenter

import androidx.fragment.app.Fragment
import com.example.edunotekotlin.entities.RepositorySharedImpl
import com.example.edunotekotlin.ui.ViewInterface
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

class NoteMainPresenterImpl(val view: ViewInterface) : NoteMainPresenter {


    private var repository = RepositorySharedImpl()
    private var fragment: Fragment? = null

    override fun init() {
        view.startLoading()
        repository.getData(object : CallBack<MutableList<OneNote>> {
            override fun onSuccess(data: MutableList<OneNote>) {
                view.writeNoteListToData(data)
                view.redraw()
                view.loaded()
            }
        })


    }

    override fun addNote(note: OneNote) {
//        TODO("Not yet implemented")
    }

    override fun updateNote(oldNote: OneNote) {
//        TODO("Not yet implemented")
    }

    override fun deleteNote(note: OneNote) {
        view.startLoading()
        repository.deleteNote(note, object : CallBack<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.loaded()

            }
        })
    }


    override fun attachFragment(fragment: Fragment) {
        this.fragment = fragment
    }

    override fun detouchFragment() {
        this.fragment = null
    }

}