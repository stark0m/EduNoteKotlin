package com.example.edunotekotlin.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.edunotekotlin.R
import com.example.kotlineasynote.entities.OneNote


class ActionFragment : Fragment() {


    lateinit var noteDescription:EditText
    lateinit var noteText:EditText
    lateinit var currentNote: OneNote

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

        writeValuesToView()

    }

    private fun writeValuesToView() {
        noteDescription.setText(currentNote.description)
        noteText.setText(currentNote.text)
    }


    companion object{
    fun newInstance(note:OneNote) = {
        ActionFragment().apply { currentNote = note }
         }
}


}