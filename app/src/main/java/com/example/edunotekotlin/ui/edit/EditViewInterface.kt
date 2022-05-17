package com.example.edunotekotlin.ui.edit

import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote

interface EditViewInterface {
    fun viewAction(callBack: CallBack<OneNote>)

}