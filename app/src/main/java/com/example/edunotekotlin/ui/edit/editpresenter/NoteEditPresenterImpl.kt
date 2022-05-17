package com.example.edunotekotlin.ui.edit.editpresenter

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.edit.ActionFragment
import com.example.edunotekotlin.ui.edit.EditViewInterface
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

class NoteEditPresenterImpl : NoteEditPresenter {
    lateinit var fragment: ActionFragment
    lateinit var fragmentView: EditViewInterface
    lateinit var currentNote: OneNote
    lateinit var buttonText :String
    override fun back() {
        fragment!!.requireActivity().supportFragmentManager.popBackStack()

    }
    constructor(fragment: ActionFragment, view: EditViewInterface, note: OneNote = OneNote(), buttonText:String = "Save") {
        this.fragment = fragment
        this.currentNote = note
        this.fragmentView = view
        this.buttonText = buttonText

    }

    override fun action(callBack: CallBack<OneNote>) {

        fragmentView.viewAction(object : CallBack<OneNote> {
            override fun onSuccess(data: OneNote) {
                currentNote = data
                callBack.onSuccess(currentNote)
            }
        })
    }


}

