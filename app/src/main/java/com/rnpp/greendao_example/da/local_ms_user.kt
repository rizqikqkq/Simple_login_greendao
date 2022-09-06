package com.rnpp.greendao_example.da

import android.content.Context
import com.rnpp.greendao_example.db.*
import com.rnpp.greendao_example.ui.adapter.UA
import java.sql.SQLException
import java.util.HashMap

class local_ms_user {
    var mTempMsUser : MutableList<ms_user> = ArrayList()
    var mMsUser : MutableList<Map<String, String>> = ArrayList()

    @Throws(SQLException::class)
    fun DAO_do_regist_user(
        context: Context,
        uid : String,
        date : String,
        create_by: String,
        update_by: String,
        email: String,
        password: String,
        name_first: String,
        name_last: String,
    ): String {
        var bIsSuccess: String
        try {
            val daoSession : DaoSession = DaoHelper.initSession(context)

            val msUser = ms_user()
            msUser.data_sync_status = "0"
            msUser.data_sync_date = date
            msUser.update_at = date
            msUser.create_at = date
            msUser.create_by = create_by
            msUser.update_by = update_by
            msUser.email = email
            msUser.password = password
            msUser.name_first = name_first
            msUser.name_last = name_last
            msUser.uid = uid


            daoSession.ms_userDao.insert(msUser)
            bIsSuccess = "1"

        } catch (ex: Exception) {
            bIsSuccess = "DAO_do_regist_user: $ex"
        }
        return bIsSuccess
    }

    @Throws(SQLException::class)
    fun DAO_do_check_user(
        context: Context,
        email: String
    ): String {
        mTempMsUser.clear()
        mMsUser.clear()

        var bIsSuccess = ""
        try {
            val daoSession : DaoSession = DaoHelper.initSession(context)

            val db = DaoHelper.initDatabase(context)
            ms_userDao.createTable(db, true)

            val tableDao = daoSession.ms_userDao

            mTempMsUser.addAll(tableDao.loadAll())
            //checking email and password
            for (_iN in mTempMsUser.indices){
                if (mTempMsUser[_iN].email.equals(email)){
                    //get intended user data
                    val datanum = HashMap<String, String>()
                    datanum["uid"] = mTempMsUser[_iN].uid
                    datanum["email"] = mTempMsUser[_iN].email

                    mMsUser.add(datanum)
                }
            }

            bIsSuccess = if (mMsUser.size > 0) "1" else "0"
        } catch (ex: Exception) {
            bIsSuccess = "DAO_do_login_user:$ex"
        }
        return bIsSuccess
    }

    @Throws(SQLException::class)
    fun DAO_do_login_user(
        context: Context,
        date : String,
        email: String,
        password: String
    ): String {
        mTempMsUser.clear()
        mMsUser.clear()

        var bIsSuccess = ""
        try {
            val daoSession : DaoSession = DaoHelper.initSession(context)

            val db = DaoHelper.initDatabase(context)
            ms_userDao.createTable(db, true)

            val tableDao = daoSession.ms_userDao

            mTempMsUser.addAll(tableDao.loadAll())
            //checking email and password
            for (_iN in mTempMsUser.indices){
                if (mTempMsUser[_iN].email.equals(email) &&
                    mTempMsUser[_iN].password.equals(password)){

                    //add login history
                    val msULH = ms_userLoginHistory()
                    msULH.data_sync_status = "0"
                    msULH.data_sync_date = date
                    msULH.update_at = date
                    msULH.create_at = date
                    msULH.email = email
                    msULH.uid = mTempMsUser[_iN].uid
                    msULH.mac = UA.getMacsAddress()

                    daoSession.ms_userLoginHistoryDao.insert(msULH)
                    //get intended user data
                    val datanum = HashMap<String, String>()
                    datanum["uid"] = mTempMsUser[_iN].uid
                    datanum["email"] = mTempMsUser[_iN].email
                    datanum["password"] = mTempMsUser[_iN].password
                    datanum["name_first"] = mTempMsUser[_iN].name_first
                    datanum["name_last"] = mTempMsUser[_iN].name_last

                    mMsUser.add(datanum)
                }
            }

            bIsSuccess = if (mMsUser.size > 0) "1" else "0"
        } catch (ex: Exception) {
            bIsSuccess = "DAO_do_login_user:$ex"
        }
        return bIsSuccess
    }

}