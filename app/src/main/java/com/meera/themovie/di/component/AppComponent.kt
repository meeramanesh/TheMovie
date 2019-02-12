package com.meera.themovie.di.component

import android.app.Application
import com.meera.themovie.TheMovieApplication
import com.meera.themovie.di.module.ActivityModule
import com.meera.themovie.di.module.ApiModule
import com.meera.themovie.di.module.FragmentModule
import com.meera.themovie.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * AppComponenet for TheMovie Application
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */

@Singleton
@Component(
    modules = [ApiModule::class,
        ViewModelModule::class, AndroidSupportInjectionModule::class,
        ActivityModule::class, FragmentModule::class]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder


        fun build(): AppComponent
    }

    fun inject(movieApplication: TheMovieApplication)

}