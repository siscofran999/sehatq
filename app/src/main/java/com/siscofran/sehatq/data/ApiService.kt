package com.siscofran.sehatq.data

import com.siscofran.sehatq.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("home")
    fun getResponse(): Single<Response>
}