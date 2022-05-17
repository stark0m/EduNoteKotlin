package com.example.edunotekotlin.ui.presenter

import androidx.fragment.app.Fragment
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

interface NoteMainPresenter {
    fun init(list: MutableList<OneNote>)
    fun addNote(note: OneNote)
    fun updateNote(oldNote: OneNote)
    fun deleteNote(note: OneNote)
    fun attachFragment(fragment: Fragment)
    fun detouchFragment()
}