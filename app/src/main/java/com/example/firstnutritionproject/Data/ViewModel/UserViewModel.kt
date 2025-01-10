package com.example.firstnutritionproject.Data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser
import com.example.nutritiontracker.Data.DataInputUserDatabase
import com.example.firstnutritionproject.Data.Respository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel (application: Application) : AndroidViewModel(application) {

    val readAll: LiveData<List<DataInputUser>>
    val totalCarbs : LiveData<Int>
    val totalProtein : LiveData<Int>
    val totalFat : LiveData<Int>
    private val repository: UserRepository

    // First to be initialized when app starts
    init {
        val userDAO = DataInputUserDatabase.getDatabase(application).dao
        repository = UserRepository(userDAO)
        readAll = repository.readData
        totalCarbs = repository.sumCarbs
        totalProtein = repository.sumProtein
        totalFat = repository.sumFat
    }

    fun addUser(user: DataInputUser) {
        //Running in background
        viewModelScope.launch {
            Dispatchers.IO
            repository.addUser(user)
        }
    }

    fun updateUser(user: DataInputUser) {
        //Running in background
        viewModelScope.launch {
            Dispatchers.IO
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: Int) {
        //Running in background
        viewModelScope.launch {
            Dispatchers.IO
            repository.deleteUser(user)
        }
    }
}