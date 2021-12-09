package com.home.thirdlaba

import com.google.gson.annotations.SerializedName


data class Users(
    @SerializedName("first_name")
    val name:String,
    @SerializedName("last_name")
    val surname:String,
    @SerializedName("avatar")
    val imageUrl:String

)
