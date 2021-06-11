package com.example.catatan.User

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CatatanRepo(application: Application) {

    private val catatanDao: CatatanDao
    private var catatans: LiveData<List<CatatanEntity>>? = null

    init {
        val db = UserDatabase.getDatabase(application.applicationContext)
        catatanDao = db.catatanDao()
        catatans = catatanDao.getCatatans()
    }

    fun getCatatans(): LiveData<List<CatatanEntity>>? {
        return catatans
    }

    fun insert(catatanEntity: CatatanEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            catatanDao.insertCatatan(catatanEntity)
        }
    }

    fun delete(catatanEntity: CatatanEntity) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                catatanDao.deleteCatatan(catatanEntity)
            }
        }
    }

    fun update(catatanEntity: CatatanEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            catatanDao.updatecatatan(catatanEntity)
        }
    }

}