package com.example.firstnutritionproject.Data.DataUserTable

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Specify the tableName
// @Parcelize and Parcelable allows for data transfer between Activity
@Parcelize
@Entity(tableName = "user_table")
data class DataInputUser(
    var nameFood: String,
    var typeFood: String,
    var amountFood: Int,
    @PrimaryKey(autoGenerate = true)
    var id : Int
) : Parcelable