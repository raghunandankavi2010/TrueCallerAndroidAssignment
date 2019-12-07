package me.raghu.raghunandan_kavi


sealed class Result{

    data class Success(val data: String) : Result()

    object ConnectionError :  Result()

    data class ServerError(val error: String) : Result()

    data class Error(val exception: Throwable) : Result()

    data class UnExpectedError(val code: Int) : Result()
}