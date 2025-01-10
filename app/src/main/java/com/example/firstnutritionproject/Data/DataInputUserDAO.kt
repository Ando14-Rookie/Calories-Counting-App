package com.example.nutritiontracker.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser

@Dao
interface DataInputUserDAO {

    // Will ignore if new data is the same
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // can be started, paused, and resume
    suspend fun addData(dataInput : DataInputUser)

    @Query("DELETE FROM user_table WHERE id is :dataID")
    suspend fun deleteData(dataID: Int)

    @Update
    suspend fun updateData (dataInput: DataInputUser)

    // Select everything from DataInputUser
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    //Return every item in the database
    fun readData() : LiveData<List<DataInputUser>>

    //Sum Carbs
    @Query ("SELECT SUM(amountFood) FROM user_table WHERE typeFood ='Carbs' ")
    fun sumCarbs(): LiveData<Int>

    //Sum Protein
    @Query ("SELECT SUM(amountFood) FROM user_table WHERE typeFood ='Protein' ")
    fun sumProtein(): LiveData<Int>

    //Sum Fats
    @Query ("SELECT SUM(amountFood) FROM user_table WHERE typeFood ='Fat' ")
    fun sumFat(): LiveData<Int>

    //Sum the calories per category
    //@Query ("SELECT typeFood, SUM(amountFood) FROM user_table GROUP BY typeFood")
    //fun sumCategory() : LiveData<List<DataInputUser>>
}