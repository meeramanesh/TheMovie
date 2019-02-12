package com.meera.themovie.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.meera.themovie.factory.ViewModelFactory
import com.meera.themovie.util.FragmentUtils
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base Activity with Type View model and binding
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */
abstract class BaseActivity<V : ViewModel, D : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: V

    protected lateinit var dataBinding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private fun getBaseViewModel() = (viewModel as BaseViewModel)


    protected abstract fun getViewModel(): Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        dataBinding.setVariable(bindingVariable, viewModel)

        FragmentUtils.init(this)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

}