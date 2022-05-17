package com.example.edunotekotlin.ui.main.mainpresenter

import androidx.fragment.app.Fragment
import com.example.kotlineasynote.entities.OneNote

interface NoteMainPresenter {
    fun init()
    fun addNote(note: OneNote)
    fun updateNote(oldNote: OneNote)
    fun deleteNote(note: OneNote)
    fun attachFragment(fragment: Fragment)
    fun detouchFragment()
}