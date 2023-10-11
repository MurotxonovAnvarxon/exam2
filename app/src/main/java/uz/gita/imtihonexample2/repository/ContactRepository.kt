package uz.gita.imtihonexample2.repository

import androidx.lifecycle.LiveData
import uz.gita.imtihonexample2.data.response.Result
import uz.gita.imtihonexample2.data.room.ContactEntity

interface ContactRepository {
    val getAllContactLiveData: LiveData<List<ContactEntity>>
    fun getAllContact()
}