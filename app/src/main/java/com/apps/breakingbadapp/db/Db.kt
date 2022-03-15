package com.apps.fullandroidcourseclassa.breakingbadapi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.apps.fullandroidcourseclassa.breakingbadapi.model.BBCharacter
import kotlinx.coroutines.internal.synchronized
import org.kodein.di.Volatile

@Database(
    entities = [BBCharacter::class],
    version = 1
)
@TypeConverter(AppTypeConverters::class)
abstract class Db :RoomDatabase() {
    abstract fun createCharacterDao():CharacterDao
    companion object{
        @Volatile
        private var INSTANCE:Db?=null
        fun getDatabase(context: Context):Db{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Db::class.java,
                    "bbapp.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}