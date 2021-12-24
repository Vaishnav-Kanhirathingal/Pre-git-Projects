package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
private val moshi = Moshi.Builder()//builds object
    .add(KotlinJsonAdapterFactory())//this is responsible for converting to and from json
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL).build()

interface MarsApiService {
    //photos is an endpoint on the server. this would return the
    //ids and image urls to the getPhotos function.
    @GET("photos")
    //this statement appends "photos" to the sever weblink which when executed
    //returns the data for photos it gets from the server to the function.
    suspend fun getPhotos(): List<MarsPhoto>
    //here, the received data gets converted to a list of MarsPhoto which
    //contains an id and an image url. the id variable has the same name as the
    //name used in the json response. But, the image url has different variable
    //names. the response has it as "img_src" while the class has it as imgSrcUrl.
    //to solve this issue, the class variable has a json name declared where the
    //json name matches the name in the server response.
}

object MarsApi {
    val retrofitService: MarsApiService
            by lazy { retrofit.create(MarsApiService::class.java) }
}

