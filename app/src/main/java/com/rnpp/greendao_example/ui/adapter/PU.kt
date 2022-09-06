package com.rnpp.greendao_example.ui.adapter

import android.content.Context
import android.preference.PreferenceManager

class PU {
    companion object {

        fun saveUserMail(arcd: String?, con: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(con)
            val prefedit = prefs.edit()
            prefedit.putString(Constants.KEY_UMAIL, arcd)
            prefedit.apply()
            return true
        }
        fun getUserMail(context: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(Constants.KEY_UMAIL, null)
        }

        fun saveUserPass(arcd: String?, con: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(con)
            val prefedit = prefs.edit()
            prefedit.putString(Constants.KEY_UPASS, arcd)
            prefedit.apply()
            return true
        }
        fun getUserPass(context: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(Constants.KEY_UPASS, null)
        }

        fun saveUserDate(arcd: String?, con: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(con)
            val prefedit = prefs.edit()
            prefedit.putString(Constants.KEY_USESD, arcd)
            prefedit.apply()
            return true
        }
        fun getUserDate(context: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(Constants.KEY_USESD, null)
        }

        fun saveUserKML(arcd: String?, con: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(con)
            val prefedit = prefs.edit()
            prefedit.putString(Constants.KEY_USKML, arcd)
            prefedit.apply()
            return true
        }
        fun getUserKML(context: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(Constants.KEY_USKML, null)
        }
    }
}
