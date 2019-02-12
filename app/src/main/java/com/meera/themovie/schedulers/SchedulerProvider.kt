package com.meera.themovie.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Android Scheduler provider for executing the asynchronous operations
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */
class SchedulerProvider : BaseScheduler {

    override fun io(): Scheduler {
        return Schedulers.newThread()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}