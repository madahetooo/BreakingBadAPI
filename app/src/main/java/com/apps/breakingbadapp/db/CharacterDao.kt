package com.apps.fullandroidcourseclassa.breakingbadapi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.fullandroidcourseclassa.breakingbadapi.model.BBCharacter

@Dao
interface CharacterDao {
    @Query("select * from character")
    fun findAll(): LiveData<List<BBCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(charList: List<BBCharacter>)
}