package com.rnpp.greendao_example.ui.adapter

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.TextView
import com.rnpp.greendao_example.R

class CLD
(var activity: Activity) {
    var dialog: Dialog? = null

    fun showDialog(clDesk: String?) {
        dialog = Dialog(activity)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.cld)
        val cl_description = dialog!!.findViewById<TextView>(R.id.tx_cl_description)

        if (clDesk != null) {
            cl_description.visibility = View.VISIBLE
            cl_description.text = clDesk
        } else {
            cl_description.visibility = View.GONE
        }

        dialog!!.show()
    }

    fun showDialog(clDesk: String?, clTitle: String?) {
        dialog = Dialog(activity)
        dialog!!.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.cld)
        val cl_description = dialog!!.findViewById<TextView>(R.id.tx_cl_description)
        if (clDesk != null) {
            cl_description.visibility = View.VISIBLE
            cl_description.text = clDesk
        } else {
            cl_description.visibility = View.GONE
        }
        dialog!!.setTitle(clTitle)
        dialog!!.show()
    }

    fun hideDialog() {
        if (dialog != null) {
            var lo: Boolean
            do {
                lo = dialog!!.isShowing
                dialog!!.dismiss()
            }while (lo)
        }
    }

    fun isShowing(): Boolean {
        return dialog!!.isShowing
    }
}