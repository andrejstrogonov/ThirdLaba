package com.home.thirdlaba

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository):ViewModel(){
    val errorMessage=MutableLiveData<String>()
    val usersList = MutableLiveData<List<Users>>()
    var job: Job?=null
    val loading=MutableLiveData<Boolean>()
    val exceptionHandler= CoroutineExceptionHandler{_, throwable ->onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value=message
        loading.value=false

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    fun getAllUsers(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch{
            val response = mainRepository.getAllUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    usersList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

}
