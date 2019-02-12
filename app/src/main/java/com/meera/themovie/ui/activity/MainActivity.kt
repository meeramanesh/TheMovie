package com.meera.themovie.ui.activity

import android.os.Bundle
import com.meera.themovie.BR
import com.meera.themovie.R
import com.meera.themovie.databinding.ActivityMainBinding
import com.meera.themovie.ui.base.BaseActivity
import com.meera.themovie.ui.viewmodel.MainActivityViewModel
import com.meera.themovie.util.NavUtils

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {


    override val layoutRes: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavUtils.pushMovieListFragment()
    }
}
