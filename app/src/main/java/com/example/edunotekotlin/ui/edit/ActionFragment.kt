package com.example.edunotekotlin.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.edit.editpresenter.NoteEditPresenter
import com.example.edunotekotlin.ui.edit.editpresenter.NoteEditPresenterImpl
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote


class ActionFragment : Fragment(), EditViewInterface {


    lateinit var noteDescription: EditText
    lateinit var noteText: EditText
    lateinit var currentNote: OneNote
    lateinit var buttonAction: Button
    lateinit var buttonCancel: Button
    lateinit var presenter: NoteEditPresenterImpl

    lateinit var clickActionLisener: View.OnClickListener


    fun setNote(note: OneNote) {
        currentNote = note

    }

    @JvmName("setPresenter1")
    fun setPresenter(presenter: NoteEditPresenter) {
        this.presenter = presenter as NoteEditPresenterImpl

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteDescription = view.findViewById(R.id.editnote_description)
        noteText = view.findViewById(R.id.editnote_text)
        buttonAction = view.findViewById(R.id.editnote_action_button)
        buttonCancel = view.findViewById(R.id.editnote_cancel_button)


        noteDescription.setText(currentNote.description)
        noteText.setText(currentNote.text)
        buttonAction.text = presenter.buttonText

        setButtonListeners()

    }

    private fun setButtonListeners() {
        buttonCancel.setOnClickListener() {
            presenter.back()
        }

        buttonAction.setOnClickListener(clickActionLisener)

    }


    override fun viewAction(callBack: CallBack<OneNote>) {

        clickActionLisener = View.OnClickListener {
            currentNote.text = noteText.text.toString()
            currentNote.description = noteDescription.text.toString()
            callBack.onSuccess(currentNote)
        }

    }


}