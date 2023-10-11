package uz.gita.imtihonexample2.presentation.screen.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.imtihonexample2.data.response.Result
import uz.gita.imtihonexample2.data.room.ContactEntity

interface ScreenViewModel {
    val contactsLiveData : LiveData<List<ContactEntity>>
    val errorMessageLiveData : LiveData<String>

    fun loadDAta()
}