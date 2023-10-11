package uz.gita.imtihonexample2.presentation.screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.imtihonexample2.data.response.Result
import uz.gita.imtihonexample2.data.room.ContactEntity
import uz.gita.imtihonexample2.repository.ContactRepository
import uz.gita.imtihonexample2.repository.ContactRepositoryImpl

class ScreenViewModelImpl : ViewModel(), ScreenViewModel {
    override val contactsLiveData = MediatorLiveData<List<ContactEntity>>()

    override val errorMessageLiveData = MutableLiveData<String>()


    private val contactRepository: ContactRepository = ContactRepositoryImpl.getInstance()


    init {
        contactsLiveData.addSource(contactRepository.getAllContactLiveData) {
            contactsLiveData.value = it
        }

        loadDAta()
    }

    override fun loadDAta() {
        contactRepository.getAllContact()
    }


}