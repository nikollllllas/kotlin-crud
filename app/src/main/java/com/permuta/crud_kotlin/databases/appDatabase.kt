package com.permuta.crud_kotlin.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.permuta.crud_kotlin.dao.PersonDAO
import com.permuta.crud_kotlin.entities.Person


@Database (entities = [Person:: class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract  fun getPersonDao(): PersonDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun  invoke (context: Context) =  instance ?: synchronized(LOCK) {
            instance ?: buildDatabase (context).also {
                instance = it
            }
        }

        private fun buildDatabase (context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase:: class.java,
            "app-database"
        ).build()
    }
}
