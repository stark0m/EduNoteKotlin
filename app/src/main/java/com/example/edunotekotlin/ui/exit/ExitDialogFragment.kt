package com.example.edunotekotlin.ui.exit

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.edunotekotlin.R
import com.example.edunotekotlin.ui.MainActivity

class ExitDialogFragment:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireActivity())
            .setCancelable(true)
            .setMessage(R.string.are_you_sure_to_cancel)
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->  })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i -> requireActivity().finish() })
            .create()

    }

    companion object{
        val TAG="TAG"
    }
}