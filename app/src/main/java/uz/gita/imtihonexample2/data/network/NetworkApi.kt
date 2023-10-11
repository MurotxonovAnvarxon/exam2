package uz.gita.imtihonexample2.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.imtihonexample2.data.response.ContactResponse

interface NetworkApi {

    @GET("?results=20")
    fun getAllContacts(): Call<ContactResponse>

}