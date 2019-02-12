package com.meera.themovie.schedulers

import android.support.annotation.NonNull
import io.reactivex.Scheduler


/**
 * Base Scheduler implementation
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */
interface BaseScheduler {

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler

}