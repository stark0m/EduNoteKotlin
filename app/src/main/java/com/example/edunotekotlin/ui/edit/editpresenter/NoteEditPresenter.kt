package com.example.edunotekotlin.ui.edit.editpresenter

import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

interface NoteEditPresenter {
    fun action(callBack: CallBack<OneNote>)
    fun back()

}