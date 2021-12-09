package com.home.thirdlaba

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllUsers()=retrofitService.getAllUsers()
}