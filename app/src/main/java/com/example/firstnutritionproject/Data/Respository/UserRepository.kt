package com.example.firstnutritionproject.Data.Respository

import androidx.lifecycle.LiveData
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser
import com.example.nutritiontracker.Data.DataInputUserDAO

// used for CRUD (Create, Read, Update, and Delete or Destroy)
class UserRepository (private val userDAO : DataInputUserDAO) {
    // Read every item in the database from User
    val readData : LiveData<List<DataInputUser>> = userDAO.readData()
    val sumCarbs : LiveData<Int> = userDAO.sumCarbs()
    val sumProtein : LiveData<Int> = userDAO.sumProtein()
    val sumFat : LiveData<Int> = userDAO.sumFat()

     suspend fun addUser (user : DataInputUser){
        userDAO.addData(user)
    }

    suspend fun updateUser (user: DataInputUser){
        userDAO.updateData(user)
    }

    suspend fun deleteUser (userID: Int){
        userDAO.deleteData(userID)
    }

}