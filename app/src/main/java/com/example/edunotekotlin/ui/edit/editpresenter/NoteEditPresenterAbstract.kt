package com.example.edunotekotlin.ui.edit.editpresenter

import androidx.fragment.app.Fragment
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.Constants
import com.example.edunotekotlin.ui.edit.ActionFragment
import com.example.edunotekotlin.ui.edit.EditViewInterface
import com.example.kotlineasynote.entities.OneNote

abstract class NoteEditPresenterAbstract : NoteEditPresenter {
    lateinit var fragment: ActionFragment
    lateinit var fragmentView: EditViewInterface
    lateinit var currentNote: OneNote
    var buttonText = "Save"
    override fun back() {
        fragment!!.requireActivity().supportFragmentManager.popBackStack()

    }
}