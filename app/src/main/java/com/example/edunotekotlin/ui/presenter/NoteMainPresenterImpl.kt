package com.example.edunotekotlin.ui.presenter

import androidx.fragment.app.Fragment
import com.example.edunotekotlin.ui.ViewInterface
import com.example.kotlineasynote.entities.OneNote

class NoteMainPresenterImpl(view:ViewInterface):NoteMainPresenter {

    private var fragment:Fragment? = null

    override fun init(list: MutableList<OneNote>) {
        TODO("Not yet implemented")
    }

    override fun addNote(note: OneNote) {
        TODO("Not yet implemented")
    }

    override fun updateNote(oldNote: OneNote) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: OneNote) {
        TODO("Not yet implemented")
    }

    override fun attachFragment(fragment: Fragment) {
       this.fragment = fragment
    }

    override fun detouchFragment() {
       this.fragment = null
    }

}