package com.example.catatan.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserViewModel(application: Application): AndroidViewModel(application) {

    private var repository = CatatanRepo(application)
    private var catatans: LiveData<List<CatatanEntity>>? = repository.getCatatans()

    fun insertCatatan(catatanEntity: CatatanEntity) {
        repository.insert(catatanEntity)
    }

    fun getCatatans(): LiveData<List<CatatanEntity>>? {
        return catatans
    }

    fun deleteCatatan(catatanEntity: CatatanEntity) {
        repository.delete(catatanEntity)
    }

    fun updateCatatan(catatanEntity: CatatanEntity) {
        repository.update(catatanEntity)
    }

}