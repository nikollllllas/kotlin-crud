package com.permuta.crud_kotlin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Person (
    var name:String = "",
    var email:String = "",
    var birthDate:String = "",
    var phone:String = "",
    var address:String = ""
) : java.io.Serializable {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}

