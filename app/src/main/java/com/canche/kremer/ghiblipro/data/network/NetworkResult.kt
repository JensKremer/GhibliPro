package com.canche.kremer.ghiblipro.data.network

sealed class NetworkResult<T : Any, P : Any>{

    class Success<T: Any, P: Any>(val data: P) : NetworkResult<T, P>()
    class Error<T: Any, P: Any>(val code: Int, val message: String?) : NetworkResult<T, P>()
    class Exception<T: Any, P: Any>(val e: Throwable) : NetworkResult<T, P>()

}
