package me.raghu.raghunandan_kavi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel() {

    private val fetchDataRepository: FetchDataRepository = FetchDataRepository()


    private val tenthChar by lazy {
        val liveData = MutableLiveData<String>()
        viewModelScope.launch {
            val one = async(Dispatchers.IO) { fetchDataRepository.fetch10thCharacter() }
            val result1 = one.await()
            when (result1) {
                is Result.Success -> {
                    liveData.value = result1.data
                }
            }
        }
        return@lazy liveData
    }

    private val everyTenthChar by lazy {
        val liveData = MutableLiveData<String>()
        viewModelScope.launch {
            val one = async(Dispatchers.IO) { fetchDataRepository.fetchEvery10thCharacter() }
            val result1 = one.await()
            liveData.value = result1

        }
        return@lazy liveData
    }

    private val wordsCount by lazy {
        val liveData = MutableLiveData<String>()
        viewModelScope.launch {
            val one = async(Dispatchers.IO) { fetchDataRepository.fetchAll() }
            val result1 = one.await()
            liveData.value = result1

        }
        return@lazy liveData
    }


    fun getTenthChar(): LiveData<String> = tenthChar

    fun getEveryTenthChar(): LiveData<String> = everyTenthChar

    fun getWordsCount(): LiveData<String> = wordsCount

}