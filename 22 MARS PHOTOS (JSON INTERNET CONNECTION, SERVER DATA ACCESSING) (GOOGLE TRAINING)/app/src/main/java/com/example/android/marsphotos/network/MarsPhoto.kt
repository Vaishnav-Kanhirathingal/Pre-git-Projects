package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
    //@Json(name = "img_src") is sed to specify the json name
    //for the variable. this will be used when the response data
    //is being converted into a MarsPhoto class object.
)

