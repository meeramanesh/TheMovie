package com.meera.themovie.data.repository

import com.meera.themovie.data.remote.response.Response
import com.meera.themovie.data.remote.response.ResponseListener
import com.meera.themovie.schedulers.BaseScheduler
import io.reactivex.Observable

/**
 * File Description
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
abstract class BaseRepository(private val scheduler: BaseScheduler) {

    /**
     * This method performs the asynchronous network request using the scheduler
     * @param observable : Observable network request
     * @param responseListener: ResponseListener Callback
     */
    protected fun <T : Any> performRequest(
        observable: Observable<T>,
        responseListener: ResponseListener<T>
    ) {
        observable.subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { responseListener.onStart() }
            .doFinally { responseListener.onFinish() }
            .subscribe({ result: T ->
                responseListener.onResponse(
                    Response.success(result)
                )
            },
                { error: Throwable? ->
                    responseListener.onResponse(
                        Response.error(
                            error.toString(),
                            null
                        )
                    )
                })
    }
}