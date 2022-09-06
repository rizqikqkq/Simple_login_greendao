package com.rnpp.greendao_example.ui.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import java.math.BigInteger
import java.net.NetworkInterface
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class UA {
    companion object {
        private var db2ePressedOnce  : Boolean = false

        private val calendar = Calendar.getInstance()
        val Day = calendar.get(Calendar.DAY_OF_MONTH)
        val Month = calendar.get(Calendar.MONTH)
        val Year = calendar.get(Calendar.YEAR)
        val ddf = SimpleDateFormat("yyyy-MM-dd HH:mm:dd.ddd")

        fun getDate(_date: String?, _type: String):String {

            val sdf : SimpleDateFormat = when {
                _type.equals("date", true) -> {
                    SimpleDateFormat("yyyy-MM-dd")
                }
                _type.equals("datetime", true) -> {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
                }
                _type.equals("time", true) -> {
                    SimpleDateFormat("HH:mm:ss.sss")
                }
                else -> {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
                }
            }
            val dat = try {
                _date ?: ddf.format(Calendar.getInstance().time)
            }catch (e:java.lang.Exception){
                "Failed to get the time"
            }

            return if (dat != "Failed to get the time"){
                sdf.format(sdf.parse(dat))
            }else {
                ""
            }
        }

        fun getDate(date: String?, codeFormat: Int): String {
            val dat = try {
                date ?: ddf.format(Calendar.getInstance().time)
            }catch (e:java.lang.Exception){
                ""
            }
            val df = SimpleDateFormat("yyyy-MM-dd mm:ss:dd")
            val _fdate = df.format(df.parse(dat)).split(" ")[0]
            val _ftime = df.format(df.parse(dat)).split(" ")[1]
            val _day = _fdate.split("-")[2]
            val _month = _fdate.split("-")[1]
            val _year = _fdate.split("-")[0]
            val _hour = _ftime.split(":")[0]
            val _minute = _ftime.split(":")[1]
            val _second = _ftime.split(":")[2]

            return when (codeFormat){
                1 -> {
                    _month+_hour+_year+_minute+_second+_day
                }
                2 -> {
                    _year+_month
                }
                3 -> {
                    _year+_month.padStart(4,'0')
                }

                4 -> {_day}
                5 -> {_month}
                6 -> {_year}
                7 -> {_hour}
                8 -> {_minute}
                9 -> {_second}

                else -> {
                    "NONE"
                }
            }
        }

        /*fun getDateFormated(_date: String?, _type: String):String {

        }*/

        fun hasText(editText:EditText, error_message:String):Boolean {
            val text = editText.text.toString().trim()
            editText.error = null
            // length 0 means there is no text
            return if (text.isEmpty()) {
                editText.error = error_message
                editText.requestFocus()
                false
            }else {
                editText.error = null
                true
            }
        }

        fun hasMinimalText(editText:EditText, error_message:String, min: Int):Boolean {
            val text = editText.text.toString().trim()
            editText.error = null
            // length 0 means there is no text
            return if (text.length < min) {
                editText.error = error_message
                editText.requestFocus()
                false
            }else {
                editText.error = null
                true
            }
        }

        fun isEditActive(et: EditText, isActive: Boolean){
            et.isActivated = isActive
            et.isClickable = isActive
            et.isLongClickable = isActive
            et.isFocusable = isActive
            et.isEnabled = isActive
        }

        fun doBread(stringMsg: String, v: View?, isSnack: Int, context: Context?) {
            if (v != null && isSnack == 1) {
                Snackbar.make(v, stringMsg, Snackbar.LENGTH_LONG).show()
            } else
                if (context != null) {
                    Toast.makeText(context, stringMsg, Toast.LENGTH_LONG).show()
                } else {
                    Log.wtf(isSnack.toString(), stringMsg)
                }
        }

        fun showError(erTitle: String?,erText: String, context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setTitle(erTitle ?: "An error has occurred")
            builder.setMessage("\n $erText")
            builder.setNeutralButton("Close") { _, _ -> }


            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        fun getMacsAddress(): String{
            val mac_address = StringBuilder()
            try {
                val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
                for (intf in interfaces) {
                    val mac = intf.getHardwareAddress()

                    for (aMac in mac) {
                        mac_address.append(String.format("%02X:", aMac))
                    }

                    if (mac_address.isNotEmpty()) {
                        mac_address.deleteCharAt(mac_address.length - 1)
                    }
                }
                return mac_address.toString()
            } catch (ex: java.lang.Exception) {}
            return mac_address.toString()
        }

        fun GenereateUserId(_email: String, _fName: String, _lName: String): String{
            val Hash1 = Base64.encodeToString((_fName+_lName).toByteArray(charset("UTF-8")), Base64.DEFAULT).replace("\n","")
            val Hash2 = Base64.encodeToString(_email.toByteArray(charset("UTF-8")), Base64.DEFAULT).replace("\n","")
            val date = getDate(null, 2)
            val day_hour = getDate(null, 4)+getDate(null, 7)

            val generated_code = "$day_hour$Hash2$date$Hash1".toByteArray(charset("UTF-16"))
            val gen_mdd5 = Base64.encodeToString(generated_code, Base64.DEFAULT).replace("\n","")

            return gen_mdd5
        }

        fun Encrypt(_key: String): String {
            val kunci = _key
            val md = MessageDigest.getInstance("MD5")
            val kagi = md.digest(kunci.toByteArray(charset("UTF-8")))
            return Base64.encodeToString(kagi, Base64.DEFAULT).replace("\n","")
        }
    }
}