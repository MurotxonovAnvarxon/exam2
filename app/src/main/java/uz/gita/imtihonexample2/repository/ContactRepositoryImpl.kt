package uz.gita.imtihonexample2.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.imtihonexample2.app.App
import uz.gita.imtihonexample2.data.network.NetworkApi
import uz.gita.imtihonexample2.data.response.ContactResponse
import uz.gita.imtihonexample2.data.response.Result
import uz.gita.imtihonexample2.data.room.ContactDao
import uz.gita.imtihonexample2.data.room.ContactDataBase
import uz.gita.imtihonexample2.data.room.ContactEntity
import uz.gita.imtihonexample2.toEntity

class ContactRepositoryImpl(val networkApi: NetworkApi) : ContactRepository {
    override val getAllContactLiveData = MutableLiveData<List<ContactEntity>>()
    private val local: ContactDao = ContactDataBase.getInstance().contactDao()

    companion object {
        private var contactRepository: ContactRepository? = null

        fun init(networkApi: NetworkApi) {
            if (contactRepository == null) {
                contactRepository = ContactRepositoryImpl(networkApi)
            }
        }

        fun getInstance(): ContactRepository = contactRepository!!
    }


    override fun getAllContact() {
        networkApi.getAllContacts().enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>,
            ) {
                if (response.isSuccessful) {
                    getAllContactLiveData.value = response.body()?.results?.map {
                        local.insertContact(it.toEntity())
                        it.toEntity()
                    }


                } else {
                    Log.d("TTT", "onResponse: error message:${response.message().toString()} ")
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                if (checkForInternet(App.instance)) {
                    getAllContact()
                } else {
                    getAllContactLiveData.value = local.getAllContact()
                }
            }
        })
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}