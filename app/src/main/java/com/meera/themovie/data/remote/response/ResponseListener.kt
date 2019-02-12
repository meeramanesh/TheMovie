package com.meera.themovie.data.remote.response

/**
 * Class for constructing the  Response as sucess or error according to the  response
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */

interface ResponseListener<T> {
    fun onStart() {
        //show loader
    }

    fun onFinish() {
        // hide loader
    }

    fun onResponse(result: Response<T>)
}