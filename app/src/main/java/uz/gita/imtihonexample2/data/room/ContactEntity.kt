package uz.gita.imtihonexample2.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class ContactEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val email:String,
    val image:String,
    val age:Int,
    val firstName:String,
    val lastName:String,
    val gender:String,
    val phone:String,
    val url:String
):Serializable
