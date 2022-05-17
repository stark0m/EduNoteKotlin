package com.example.edunotekotlin.ui.edit.editpresenter

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.edit.ActionFragment
import com.example.edunotekotlin.ui.edit.EditViewInterface
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

class NoteEditPresenterEditNote : NoteEditPresenterAbstract {
    constructor(fragment: ActionFragment, view: EditViewInterface, note: OneNote = OneNote()) {
        this.fragment = fragment
        this.currentNote = note
        this.fragmentView = view

    }

    override fun action(callBack: CallBack<OneNote>) {
//        currentNote.description=fragment.requireView().findViewById<EditText>(R.id.editnote_description).text.toString()
//        currentNote.text=fragment.requireView().findViewById<EditText>(R.id.editnote_text).text.toString()

        fragmentView.viewAction(object : CallBack<OneNote> {
            override fun onSuccess(data: OneNote) {
                currentNote = data
                callBack.onSuccess(currentNote)
            }
        })
    }


}

