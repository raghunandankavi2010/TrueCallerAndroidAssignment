package me.raghu.raghunandan_kavi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val fetchDataRepository: FetchDataRepository = FetchDataRepository()

    var tenthChar: MutableLiveData<String> = MutableLiveData()
    var everyTenthChar: MutableLiveData<String> = MutableLiveData()
    var wordsCount: MutableLiveData<String> = MutableLiveData()

    @ExperimentalCoroutinesApi
    fun launchDataLoad() {
        viewModelScope.launch {
            fetchDataRepository.fetchTenthChar().collect {
                tenthChar.value = it
            }
            fetchDataRepository.fetchEveryTenthChar().collect {
                everyTenthChar.value = it
            }

            fetchDataRepository.fetchCountWords().collect {
                wordsCount.value = it
            }
        }
    }
}