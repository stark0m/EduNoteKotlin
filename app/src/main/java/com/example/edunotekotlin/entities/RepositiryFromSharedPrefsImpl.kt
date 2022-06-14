package com.example.edunotekotlin.entities


import android.content.Context
import android.content.SharedPreferences
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RepositiryFromSharedPrefsImpl : Repository {

    private lateinit var sharedPrefs: SharedPreferences

    val gson = Gson()


    constructor (context: Context) {

        sharedPrefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val value = sharedPrefs.getString(KEY_SAVED_NOTES, "[]")
        val typeToken = object : TypeToken<MutableList<OneNote>>() {}.type
        dataRepository.addAll(gson!!.fromJson(value, typeToken))


    }

    private fun saveNotes() {


        val typeToken = object : TypeToken<MutableList<OneNote>>() {}.type
        var value = gson!!.toJson(dataRepository, typeToken)
        sharedPrefs.edit()
            .putString(KEY_SAVED_NOTES, value)
            .apply()
    }

    override fun getData(callback: CallBack<MutableList<OneNote>>) {
        callback.onSuccess(dataRepository)
    }

    override fun addNote(note: OneNote, callback: CallBack<Boolean>) {
        dataRepository.add( note)
        saveNotes()
        callback.onSuccess(true)
    }

    override fun updateNote(oldNote: OneNote, newNote: OneNote, callback: CallBack<Boolean>) {

        val position = dataRepository.indexOf(oldNote)
        dataRepository.set(position, newNote)
        saveNotes()
        callback.onSuccess(true)

    }


    override fun deleteNote(note: OneNote, callback: CallBack<Boolean>) {
        val position = dataRepository.indexOf(note)
        dataRepository.removeAt(position)
        saveNotes()
        callback.onSuccess(true)
    }

    companion object {
        private var dataRepository: MutableList<OneNote> = mutableListOf()
        var isReaded = false
        val PREFS_FILE_NAME = "NOTESFIFE"
        val KEY_SAVED_NOTES = "SAVED_NOTES"
    }
}


