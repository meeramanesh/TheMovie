package com.meera.themovie.data.remote.response

/**
 * Class for constructing the  Response as sucess or error according to the  response
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */
class Response<T> private constructor(val responseStatus: ResponseStatus, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T): Response<T> {
            return Response(ResponseStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Response<T> {
            return Response(ResponseStatus.ERROR, data, msg)
        }

    }
}