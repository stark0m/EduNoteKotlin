package com.example.edunotekotlin.ui.main.mainpresenter

import androidx.fragment.app.Fragment
import com.example.kotlineasynote.entities.OneNote
import java.text.FieldPosition

interface NoteMainPresenter {
    fun init()
    fun addNote()
    fun updateNote(oldNote: OneNote)
    fun deleteNote(note: OneNote)
    fun setSelectedNote(oldNote: OneNote)
    fun getSelectedNote():OneNote?
    fun setSelectedNotePosition(position: Int)
    fun getSelectedNotePosition():Int?
    fun attachFragment(fragment: Fragment)
    fun detouchFragment()
}