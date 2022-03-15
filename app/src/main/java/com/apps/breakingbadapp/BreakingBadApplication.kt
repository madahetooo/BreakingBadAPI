package com.apps.fullandroidcourseclassa.breakingbadapi
import android.app.Application
import com.apps.fullandroidcourseclassa.breakingbadapi.db.Db
import com.apps.fullandroidcourseclassa.breakingbadapi.repository.CharacterRepository
import org.kodein.di.Kodein.Companion.lazy

class BreakingBadApplication : Application() {

    val database by lazy{
        Db.getDatabase(this@BreakingBadApplication)
    }
    val charachterRepository by lazy {
        CharacterRepository(database.createCharacterDao())
    }
}