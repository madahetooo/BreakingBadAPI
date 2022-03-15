package com.apps.fullandroidcourseclassa.breakingbadapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "character")
data class BBCharacter(
    @PrimaryKey
    @SerialName("char_id")
    val id:Int,
    val name:String,
    val birthday:String,
    val occupation:Array<String>,
    val img:String?,
    val nickname:String,
    val status:String
)