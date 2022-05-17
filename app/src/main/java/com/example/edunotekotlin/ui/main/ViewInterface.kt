package com.example.edunotekotlin.ui.main

import com.example.kotlineasynote.entities.OneNote

interface ViewInterface {
    fun redraw()
    fun redrawRecyclerInPosition(position:Int)
    fun writeNoteListToData(list: MutableList<OneNote>)
    fun startLoading()
    fun loaded()
    fun addNote(newNote:OneNote)
}