package com.example.edunotekotlin.ui.main.mainpresenter

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.Constants
import com.example.edunotekotlin.entities.RepositiryFromSharedPrefsImpl
import com.example.edunotekotlin.entities.Repository
import com.example.edunotekotlin.entities.RepositorySharedImpl
import com.example.edunotekotlin.ui.edit.ActionFragment
import com.example.edunotekotlin.ui.edit.editpresenter.NoteEditPresenterImpl
import com.example.edunotekotlin.ui.main.ViewInterface
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

class NoteMainPresenterImpl : NoteMainPresenter {

    constructor (view: ViewInterface,cont:Context) : this(view) {

        repository = RepositiryFromSharedPrefsImpl(cont)
        this.viewMain = view

    }

    constructor(view: ViewInterface) {
        repository = RepositorySharedImpl()
        this.viewMain = view

    }

    private lateinit var viewMain :ViewInterface
    private lateinit var repository :Repository
    private var fragment: Fragment? = null
    private var selNote: OneNote? = null
    private var selNotePosition: Int? = null


    override fun init() {
        viewMain.startLoading()



        repository.getData(object : CallBack<MutableList<OneNote>> {
            override fun onSuccess(data: MutableList<OneNote>) {
                viewMain.writeNoteListToData(data)
                viewMain.redraw()
                viewMain.loaded()
            }
        })


    }

    override fun addNote() {
        val editableFragment = ActionFragment()
        var newNote = OneNote()
        editableFragment.setNote(newNote)

        editableFragment.setPresenter(
            NoteEditPresenterImpl(
                editableFragment,
                editableFragment,
                newNote,
                buttonText = "ADD"
            )
        )
        fragment!!.requireActivity().supportFragmentManager.beginTransaction()
            .hide(fragment!!)
            .add(R.id.fragment_container, editableFragment)
            .addToBackStack(Constants.EDIT_NOTE)
            .commit()


        editableFragment.presenter.action(object : CallBack<OneNote> {
            override fun onSuccess(noteReceived: OneNote) {
                viewMain.startLoading()

                repository.addNote(noteReceived, object : CallBack<Boolean> {
                    override fun onSuccess(data: Boolean) {
                        viewMain.loaded()
                        viewMain.addNote(noteReceived)
                    }
                })
                fragment!!.requireActivity().supportFragmentManager.popBackStack();
                viewMain.redraw()
            }
        })
//        TODO("Not yet implemented")
    }

    override fun updateNote(oldNote: OneNote) {

        val editableFragment = ActionFragment()
        editableFragment.setNote(oldNote)

        editableFragment.setPresenter(
            NoteEditPresenterImpl(
                editableFragment,
                editableFragment,
                oldNote
            )
        )
        fragment!!.requireActivity().supportFragmentManager.beginTransaction()
            .hide(fragment!!)
            .add(R.id.fragment_container, editableFragment)
            .addToBackStack(Constants.EDIT_NOTE)
            .commit()


        editableFragment.presenter.action(object : CallBack<OneNote> {
            override fun onSuccess(data: OneNote) {
                viewMain.startLoading()
                repository.updateNote(oldNote, data, object : CallBack<Boolean> {
                    override fun onSuccess(data: Boolean) {
                        viewMain.loaded()

                    }
                })
                fragment!!.requireActivity().supportFragmentManager.popBackStack();
                viewMain.redraw()
            }
        })


    }

    override fun deleteNote(note: OneNote) {
        viewMain.startLoading()

        repository.deleteNote(note, object : CallBack<Boolean> {
            override fun onSuccess(data: Boolean) {

                viewMain.loaded()

            }
        })
    }

    override fun setSelectedNote(note: OneNote) {
        selNote = note
    }

    override fun getSelectedNote(): OneNote? = selNote

    override fun setSelectedNotePosition(position: Int) {
        selNotePosition = position
    }

    override fun getSelectedNotePosition(): Int? = selNotePosition


    override fun attachFragment(fragment: Fragment) {
        this.fragment = fragment
    }

    override fun detouchFragment() {
        this.fragment = null
    }



}