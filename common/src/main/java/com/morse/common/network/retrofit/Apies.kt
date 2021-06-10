package com.morse.common.network.retrofit

import retrofit2.http.GET

interface Apies {

    @GET(value = "you_api_here")
    fun loadData ()

}