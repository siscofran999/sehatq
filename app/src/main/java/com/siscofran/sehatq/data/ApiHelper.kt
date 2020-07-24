package com.siscofran.sehatq.data

import com.siscofran.sehatq.data.model.Response
import io.reactivex.Single

interface ApiHelper {
    fun getDataHome(): Single<Response>
}