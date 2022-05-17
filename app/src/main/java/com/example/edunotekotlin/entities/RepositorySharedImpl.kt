package com.example.edunotekotlin.entities

import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import java.util.*
import javax.security.auth.callback.Callback

class RepositorySharedImpl : Repository {


    override fun getData(callback: CallBack<MutableList<OneNote>>) {

        callback.onSuccess(
            listOf<OneNote>(
                OneNote(description = "Berlin", text = "Berlin is the capital"),
                OneNote(description = "Paris", text = "Paris is the capital"),
                OneNote(description = "London", text = "London is the capital"),
                OneNote(description = "Moscow", text = "Moscow is the capital"),
                OneNote(description = "SPB", text = "SPB is the capital"),
                OneNote(description = "Dallas", text = "Dallas is the capital"),
                OneNote(description = "Houston", text = "Houston is the capital"),


                ).toMutableList()
        )
    }


    override fun addNote(note: OneNote, callback: CallBack<Boolean>) {
        callback.onSuccess(true)
    }

    override fun updateNote(oldNote: OneNote, newNote: OneNote, callback: CallBack<Boolean>) {
        callback.onSuccess(true)
    }

    override fun deleteNote(note: OneNote, callback: CallBack<Boolean>) {
        callback.onSuccess(true)
    }


}