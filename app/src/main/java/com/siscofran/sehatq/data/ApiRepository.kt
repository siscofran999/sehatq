package com.siscofran.sehatq.data

import com.siscofran.sehatq.data.model.Response
import io.reactivex.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) : ApiHelper{

    override fun getDataHome(): Single<Response> = apiService.getResponse()
}