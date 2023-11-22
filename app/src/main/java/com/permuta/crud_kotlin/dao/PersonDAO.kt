package com.permuta.crud_kotlin.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.permuta.crud_kotlin.entities.Person


@Dao
interface PersonDAO {
    @Insert
    suspend fun  addPerson (Person: Person)

    @Query("SELECT * FROM  person ORDER BY id DESC")
    suspend fun  getALLPerson(): List<Person>

    @Update
    suspend fun  updatePerson (person: Person)

    @Delete
    suspend fun  deletePerson (person: Person)
}