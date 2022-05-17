package com.example.edunotekotlin.ui.main.mainpresenter

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.edunotekotlin.R
import com.example.edunotekotlin.entities.Constants
import com.example.edunotekotlin.entities.RepositorySharedImpl
import com.example.edunotekotlin.ui.edit.ActionFragment
import com.example.edunotekotlin.ui.edit.editpresenter.NoteEditPresenterEditNote
import com.example.edunotekotlin.ui.main.ViewInterface
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

class NoteMainPresenterImpl(val view: ViewInterface) : NoteMainPresenter {


    private var repository = RepositorySharedImpl()
    private var fragment: Fragment? = null

    override fun init() {
        view.startLoading()
        repository.getData(object : CallBack<MutableList<OneNote>> {
            override fun onSuccess(data: MutableList<OneNote>) {
                view.writeNoteListToData(data)
                view.redraw()
                view.loaded()
            }
        })


    }

    override fun addNote(note: OneNote) {
//        TODO("Not yet implemented")
    }

    override fun updateNote(oldNote: OneNote) {
        val editableFragment = ActionFragment()
        editableFragment.setNote(oldNote)
        editableFragment.setPresenter(NoteEditPresenterEditNote(editableFragment,editableFragment,oldNote))
        fragment!!.requireActivity().supportFragmentManager.beginTransaction()
            .hide(fragment!!)
            .add(R.id.fragment_container,editableFragment)
            .addToBackStack(Constants.EDIT_NOTE)
            .commit()


        editableFragment.presenter.action(object :CallBack<OneNote> {
            override fun onSuccess(data: OneNote) {
                view.startLoading()
                repository.updateNote(oldNote,data,object : CallBack<Boolean>{
                    override fun onSuccess(data: Boolean) {
                        view.loaded()

                    }
                })
                fragment!!.requireActivity().supportFragmentManager.popBackStack();
                Toast.makeText(fragment!!.context, "clicked edit", Toast.LENGTH_SHORT).show()
                view.redraw()
            }
        })



    }

    override fun deleteNote(note: OneNote) {
        view.startLoading()
        repository.deleteNote(note, object : CallBack<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.loaded()

            }
        })
    }


    override fun attachFragment(fragment: Fragment) {
        this.fragment = fragment
    }

    override fun detouchFragment() {
        this.fragment = null
    }

}