package uz.gita.imtihonexample2.data.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.imtihonexample2.app.App

object Network {
    private val mClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.instance).build())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(mClient)
        .build()

    fun getNetworkApi(): NetworkApi = retrofit.create(NetworkApi::class.java)


}