package com.rnpp.greendao_example.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.greenrobot.greendao.database.Database

object DaoHelper {

    val _LOCAL_DB = "rnpp_local_db"

    fun initSession(context: Context?): DaoSession {
        val helper: DaoMaster.DevOpenHelper = DaoMaster.DevOpenHelper(context, _LOCAL_DB, null)
        val daoMaster = DaoMaster(helper.writableDatabase)
        return daoMaster.newSession()
    }

    fun initDatabase(context: Context): Database {
        val helper: DaoMaster.DevOpenHelper = DaoMaster.DevOpenHelper(context, _LOCAL_DB, null)
        val daoMaster = DaoMaster(helper.writableDatabase)
        return daoMaster.database
    }
}