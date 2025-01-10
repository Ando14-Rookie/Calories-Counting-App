package com.example.nutritiontracker.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser

@Database(
    entities = [DataInputUser::class],
    version = 1,
    exportSchema = false
)
abstract class DataInputUserDatabase: RoomDatabase() {
    abstract val dao : DataInputUserDAO

    companion object{
        //Making sure only 1 instance is created
        @Volatile
        private var Instance : DataInputUserDatabase? =null

        // Create A single Instance of Database
        fun getDatabase(context: Context) : DataInputUserDatabase {
            val tempInstance = Instance
            if (tempInstance != null){
                return tempInstance
            }
        // If it hasn't been made, then do
            synchronized(this){
                val INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DataInputUserDatabase::class.java,
                    "user_table").build()
                Instance = INSTANCE
                return INSTANCE
            }
        }
    }
}