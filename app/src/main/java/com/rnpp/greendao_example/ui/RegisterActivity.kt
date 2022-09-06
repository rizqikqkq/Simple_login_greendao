package com.rnpp.greendao_example.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.rnpp.greendao_example.R
import com.rnpp.greendao_example.da.local_ms_user
import com.rnpp.greendao_example.ui.adapter.CLD
import com.rnpp.greendao_example.ui.adapter.UA
import java.lang.Exception

@SuppressLint("StaticFieldLeak")
class RegisterActivity : AppCompatActivity() {
    private var oContext = this@RegisterActivity
    private var ocLoMsUser = local_ms_user()
    private lateinit var mLoDialog : CLD

    lateinit var mEtRegisterFirstName : EditText
    lateinit var mEtRegisterLastName : EditText
    lateinit var mEtRegisterEmail : EditText
    lateinit var mEtRegisterPassword : EditText
    lateinit var mEtRegisterPasswordConfirm : EditText
    lateinit var mCbRegisterAgree : CheckBox
    lateinit var mTxRegisterAgreement : TextView
    lateinit var mBtRegisterConfirm : Button
    lateinit var mIvRegisterCancelSignup : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initData()
        initView()
        initListener()
    }

    private fun initData() {
        //TODO("Not yet implemented")
    }

    private fun initView() {
        mLoDialog = CLD(this)
        mEtRegisterFirstName = findViewById(R.id.et_register_first_name)
        mEtRegisterLastName = findViewById(R.id.et_register_last_name)
        mEtRegisterEmail = findViewById(R.id.et_register_email)
        mEtRegisterPassword = findViewById(R.id.et_register_password)
        mEtRegisterPasswordConfirm = findViewById(R.id.et_register_password_confirm)
        mCbRegisterAgree = findViewById(R.id.cb_register_agree)
        mTxRegisterAgreement = findViewById(R.id.tx_register_agreement)
        mBtRegisterConfirm = findViewById(R.id.bt_register_confirm)
        mIvRegisterCancelSignup = findViewById(R.id.iv_register_cancel_signup)
    }

    private fun initListener() {
        mIvRegisterCancelSignup.setOnClickListener{ v ->
            finish()
        }
        mBtRegisterConfirm.setOnClickListener{ v ->
            doTryRegisterUser()
        }
    }

    private fun doTryRegisterUser() {
        mEtRegisterPasswordConfirm.error = null

        when{
            !UA.hasText(mEtRegisterFirstName, "Fill First Name") -> {}
            !UA.hasText(mEtRegisterLastName, "Fill last Name") -> {}
            !UA.hasText(mEtRegisterEmail, "Fill Email") -> {}
            !UA.hasText(mEtRegisterPassword, "Fill Password") -> {}
            !UA.hasText(mEtRegisterPasswordConfirm, "Retype the password") -> {}
            mEtRegisterPassword.text.toString() != mEtRegisterPasswordConfirm.text.toString() -> {
                mEtRegisterPasswordConfirm.error = "Password does not match!"
            }
            !mCbRegisterAgree.isChecked -> {
                UA.showError("Attention!","Please check the agreement to use the app.", oContext)
            }

            else -> {
                coVFrstName = mEtRegisterFirstName.text.toString()
                coVLastName = mEtRegisterLastName.text.toString()
                coVMail = mEtRegisterEmail.text.toString()
                coVPass = UA.Encrypt(mEtRegisterPassword.text.toString())

                DoRegistUser().execute()
            }
        }
    }

    inner class DoRegistUser : AsyncTask<String, String, String>() {

        override fun onPostExecute(result: String?) {
            mLoDialog.hideDialog()
            when (result){
                "1" -> {
                    UA.showError("Success.", "Please login using the registered account to use the app.", oContext)
                    val accountsIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(accountsIntent)
                    finish()
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

            val _uid = UA.GenereateUserId(
                coVMail,
                coVFrstName,
                coVLastName
            )

            return try{
                if (ocLoMsUser.DAO_do_check_user(oContext, coVMail) != "1"){
                    ocLoMsUser.DAO_do_regist_user(
                        oContext,
                        _uid,
                        UA.getDate(null,"datetime"),
                        _uid,
                        _uid,
                        coVMail,
                        coVPass,
                        coVFrstName,
                        coVLastName
                    )
                }else {
                    "This email is already associate with another user. "
                }
            }catch (e: Exception){
                "$e"
            }
        }
    }

    companion object {
        var coVFrstName = "NONE"
        var coVLastName = "NONE"
        var coVMail = "NONE"
        var coVPass = "NONE"
    }
}