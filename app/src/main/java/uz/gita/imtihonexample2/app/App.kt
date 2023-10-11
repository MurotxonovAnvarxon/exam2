package uz.gita.imtihonexample2.app

import android.app.Application
import android.content.Context
import uz.gita.imtihonexample2.data.network.Network
import uz.gita.imtihonexample2.data.room.ContactDataBase
import uz.gita.imtihonexample2.repository.ContactRepositoryImpl

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        val contactApi = Network.getNetworkApi()
        ContactDataBase.init(this)
        ContactRepositoryImpl.init(contactApi)
    }


    companion object {
        lateinit var instance: Context
            private set
    }
}