package com.apps.fullandroidcourseclassa.breakingbadapi.repository

import androidx.lifecycle.LiveData
import com.apps.fullandroidcourseclassa.breakingbadapi.db.CharacterDao
import com.apps.fullandroidcourseclassa.breakingbadapi.model.BBCharacter
import com.apps.fullandroidcourseclassa.breakingbadapi.service.BreakingBadNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {
    val characters:LiveData<List<BBCharacter>> = characterDao.findAll()

    suspend fun refreshCharacters(){
        withContext(Dispatchers.IO){
            val characters = BreakingBadNetwork.service.getCharacters()
            characterDao.insertAll(characters)
        }
    }
}