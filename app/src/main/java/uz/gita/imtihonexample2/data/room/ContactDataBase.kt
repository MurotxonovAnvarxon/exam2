package uz.gita.imtihonexample2.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactDataBase: RoomDatabase() {
   abstract  fun contactDao(): ContactDao

    companion object{
        private lateinit var instance: ContactDataBase

        fun init(context: Context){
            if (!Companion::instance.isInitialized)
                instance = Room.databaseBuilder(context.applicationContext,
                    ContactDataBase::class.java,"contact.db").allowMainThreadQueries().build()
        }
        fun getInstance()= instance
    }

}