package com.meera.themovie.util

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.meera.themovie.R

@SuppressLint("StaticFieldLeak")

/**
 * Fragment utility class responsible for doing all fragment related transactions
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovieApp1
 */
object FragmentUtils {


    private lateinit var context: AppCompatActivity

    fun init(context: AppCompatActivity) {
        this.context = context
    }


    /**
     * The method for replacing a new fragment
     * @param fragment: Fragment to be added
     * @param id: Fragment container ID
     * @param addToBackStack: Flag indicating whether to add to backstack or not
     */
    fun replaceFragment(fragment: Fragment, id: Int, addToBackStack: Boolean, animationType: FragmentAnimation) {
        val fragmentManager = context.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when {
            animationType === FragmentAnimation.TRANSITION_POP -> transaction.setCustomAnimations(
                R.anim.anim_enter,
                R.anim.anim_exit,
                R.anim.anim_pop_enter,
                R.anim.anim_pop_exit
            )
        }

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.canonicalName)

        transaction.replace(id, fragment, fragment.javaClass.canonicalName)
        transaction.commit()
    }


    /**
     * The method for adding a new fragment
     * @param activity : Parent Activity
     * @param frag: Fragment to be added
     * @param id: Fragment container ID
     * @param addtoBackStack: Flag indicating whether to add to backstack or not
     */
    fun addFragment(activity: AppCompatActivity, frag: Fragment, id: Int, addtoBackStack: Boolean) {
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.anim_enter,
            R.anim.anim_exit,
            R.anim.anim_pop_enter,
            R.anim.anim_pop_exit
        )

        if (addtoBackStack)
            transaction.addToBackStack(frag.javaClass.canonicalName)
        transaction.add(id, frag, frag.javaClass.canonicalName)
        transaction.commit()
    }


    /**
     * This method checks whether the specified fragment is the top fragment or not
     * @param activity: Parent Activity
     * @param fragmentTag : Fragment Tag Name
     */
    fun isCurrentTopFragment(activity: AppCompatActivity, fragmentTag: String): Boolean {
        val fragmentManager = activity.supportFragmentManager
        val tag = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
        return fragmentTag.equals(tag, ignoreCase = true)
    }

//    /**
//     * This method pops the backstack till the specified fragment
//     * @param activity : Parent activity
//     * @param tagname: Fragment Tag Name
//     */
//    fun popToProvidedFragment(activity: AppCompatActivity, tagname: String) {
//        try {
//            activity.supportFragmentManager.popBackStackImmediate(tagname, 0)
//        } catch (e: IndexOutOfBoundsException) {
//            Timber.log(Log.ASSERT, e.message)
//        } catch (e: NullPointerException) {
//            Timber.log(Log.ASSERT, e.message)
//        }
//
//    }

    /**
     * This method clears the whole backstack including the current fragment
     * @param activity: Parent activity
     */
    fun clearBackStackInclusive(activity: AppCompatActivity) {
        if (activity.supportFragmentManager.backStackEntryCount == 0)
            return
        val entry = activity.supportFragmentManager.getBackStackEntryAt(
            0
        )
        activity.supportFragmentManager.popBackStack(
            entry.id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        activity.supportFragmentManager.executePendingTransactions()

    }

    /**
     * This method clears the backstack
     */
    fun clearBackStack() {
        while (context.supportFragmentManager.backStackEntryCount != 0) {
            context.supportFragmentManager.popBackStackImmediate()
        }
    }

    /**
     * This method pops the immediate fragment
     */
    fun popBackStackImmediate(activity: AppCompatActivity?) {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

    /**
     * This method pops the immediate fragment
     */
    fun popBackStack(activity: FragmentActivity?) {
        activity?.supportFragmentManager?.popBackStack()
    }

    /**
     * This method pops the immediate fragment
     */
    fun popBackStack() {
        context.supportFragmentManager?.popBackStack()
    }

    /**
     * Pops fragment backstack till tag
     */
    fun popBackStackByTag(tag: String) {
        context.supportFragmentManager?.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}