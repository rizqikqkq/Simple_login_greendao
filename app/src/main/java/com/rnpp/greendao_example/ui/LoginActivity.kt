package com.rnpp.greendao_example.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.rnpp.greendao_example.R
import com.rnpp.greendao_example.da.local_ms_user
import com.rnpp.greendao_example.ui.adapter.CLD
import com.rnpp.greendao_example.ui.adapter.PU
import com.rnpp.greendao_example.ui.adapter.UA
import java.lang.Exception

@SuppressLint("StaticFieldLeak")
class LoginActivity : AppCompatActivity() {
    private var oContext = this@LoginActivity
    private var ocLoMsUser = local_ms_user()
    private lateinit var mLoDialog : CLD

    lateinit var mTfetEmail : TextInputLayout
    lateinit var mEtEmail : EditText
    lateinit var mTfetPassword : TextInputLayout
    lateinit var mEtPassword : EditText
    lateinit var mBtLogin : Button
    lateinit var mTvbtSignup : TextView
    lateinit var mLyLoginButton : LinearLayout
    lateinit var mChkbxLoginKeepmeLoggedIn : CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initData()
        initView()
        initListener()
    }

    private fun initView() {
        mLoDialog = CLD(this)

        mLyLoginButton = findViewById(R.id.ly_login_button)
        mChkbxLoginKeepmeLoggedIn = findViewById(R.id.chkbx_login_keep_me_logged_in)
        mTfetEmail = findViewById(R.id.tfet_email)
        mEtEmail = findViewById(R.id.et_email)
        mTfetPassword = findViewById(R.id.tfet_password)
        mEtPassword = findViewById(R.id.et_password)
        mBtLogin = findViewById(R.id.bt_login)
        mTvbtSignup = findViewById(R.id.tvbt_signup)

        mLyLoginButton.requestFocus()
    }

    private fun initData() {
        //TODO DO THIS
    }

    private fun initListener() {
        mBtLogin.setOnClickListener { v ->
            doTryLoginUser()
        }
        mTvbtSignup.setOnClickListener { v ->
            val accountsIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(accountsIntent)
        }
    }

    private fun doTryLoginUser() {
        if(UA.hasText(mEtEmail, "Harap masukkan email!") &&
                UA.hasText(mEtPassword, "Harap masukkan password!")){
            DoLoginUser(mEtEmail.text.toString(), mEtPassword.text.toString()).execute()
        }
    }

    inner class DoLoginUser(val _mail: String, val _pass: String) : AsyncTask<String, String, String>() {

        override fun onPostExecute(result: String?) {
            mLoDialog.hideDialog()
            when (result){
                "1" -> {
                    coVEmail = _mail
                    coVPassword = _pass

                    coVUID
                    coVFrstName
                    coVLastName

                    if (mChkbxLoginKeepmeLoggedIn.isChecked){
                        setLoginCredential(true, oContext)
                    }else {

                    }
                }
                "0" -> {
                    UA.doBread(
                        "No data.",
                        null,
                        0,
                        oContext)
                }
                else -> {
                    UA.showError("An Error has Occurred!", "$result",oContext)
                }
            }
        }

        override fun onPreExecute() {
            mLoDialog.showDialog("Menyimpan Data User.")
        }
        override fun doInBackground(vararg params: String?): String {
            return try{
                ocLoMsUser.DAO_do_login_user(
                    oContext,
                    UA.getDate(null, "datetime"),
                    _mail,
                    _pass
                )
            }catch (e: Exception){
                "$e"
            }
        }
    }

    private fun setLoginCredential(_save: Boolean, _context: LoginActivity) {
        if (_save){
            PU.saveUserMail(coVEmail, _context)
            PU.saveUserPass(coVPassword, _context)
            PU.saveUserDate(UA.getDate(null, "datetime"), _context)
            //PU.saveUserKML()
        }else {
            PU.saveUserMail(null, _context)
            PU.saveUserPass(null, _context)
            PU.saveUserMail(null, _context)

        }
    }



    companion object {
        var coVUID = "NONE"
        var coVEmail = "NONE"
        var coVPassword = "NONE"
        var coVFrstName = "NONE"
        var coVLastName = "NONE"
    }
}