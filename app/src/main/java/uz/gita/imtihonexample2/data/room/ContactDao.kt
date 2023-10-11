package uz.gita.imtihonexample2.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Query("Select * from contacts")
    fun getAllContact(): List<ContactEntity>

    @Insert
    fun insertContact(contactEntity: ContactEntity)
}