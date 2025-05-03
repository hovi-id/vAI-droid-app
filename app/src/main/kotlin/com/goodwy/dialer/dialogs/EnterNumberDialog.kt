package com.goodwy.dialer.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.goodwy.dialer.R
import com.goodwy.dialer.interfaces.OnInputListener


class EnterNumberDialog(private val listener: OnInputListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.layout_enter_number, null)


        val builder = AlertDialog.Builder(requireContext())
//        val view = LayoutInflater.from(context).inflate(R.layout.layout_enter_number, null)
        val editText = view.findViewById<EditText>(R.id.editTextInput)
        val buttonOk = view.findViewById<Button>(R.id.buttonOk)

        builder.setView(view)
        val dialog = builder.create()

        buttonOk.setOnClickListener {
            val input = editText.text.toString()
            listener.onInputReceived(input)
            dialog.dismiss()
        }

        return dialog
    }
}
