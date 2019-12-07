package me.raghu.raghunandan_kavi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val fetchDataRepository: FetchDataRepository = FetchDataRepository()

    var tenthChar: MutableLiveData<String> = MutableLiveData()
    var everyTenthChar: MutableLiveData<String> = MutableLiveData()
    var wordsCount: MutableLiveData<String> = MutableLiveData()

    @ExperimentalCoroutinesApi
    fun launchDataLoad() {
        viewModelScope.launch {
            //parallel
            val one = async(Dispatchers.IO) { fetchDataRepository.fetch10thCharacter() }
            val two = async(Dispatchers.IO) { fetchDataRepository.fetchEvery10thCharacter() }
            val three = async(Dispatchers.IO) { fetchDataRepository.fetchAll() }

            val result1 = one.await()
            val result2 = two.await()
            val result3 = three.await()

            tenthChar.value = result1
            everyTenthChar.value = result2
            wordsCount.value = result3

            // sequential
            /*fetchDataRepository.fetchTenthChar().collect {
                tenthChar.value = it
            }
            fetchDataRepository.fetchEveryTenthChar().collect {
                everyTenthChar.value = it
            }

            fetchDataRepository.fetchCountWords().collect {
                wordsCount.value = it
            }*/
        }
    }
}