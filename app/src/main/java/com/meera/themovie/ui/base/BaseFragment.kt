package com.meera.themovie.ui.base

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * File Description
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */
abstract class BaseFragment<V : ViewModel, D : ViewDataBinding> : Fragment() {

    protected lateinit var activity: Activity

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: V

    protected lateinit var dataBinding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    protected abstract fun getViewModel(): Class<V>

    abstract fun getTitle(): String


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
    }

    override fun onResume() {
        super.onResume()
        activity.setTitle(getTitle())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dataBinding.setLifecycleOwner(this)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
    }
}