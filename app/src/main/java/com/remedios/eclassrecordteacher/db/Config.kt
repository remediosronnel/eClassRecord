package com.remedios.eclassrecordteacher.db


import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.remedios.eclassrecordteacher.R


object Config {

    private var dialog : androidx.appcompat.app.AlertDialog? = null

    fun showDialog(context: Context){
        dialog = MaterialAlertDialogBuilder(context)
            .setView(R.layout.loading_layout)
            .setCancelable(false)
            .create()
        dialog!!.show()
    }
    fun hideDialog(){
        dialog!!.dismiss()
    }

}