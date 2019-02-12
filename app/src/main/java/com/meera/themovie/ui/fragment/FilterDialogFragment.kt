package com.meera.themovie.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.meera.themovie.R
import com.meera.themovie.configuration.Constants
import kotlinx.android.synthetic.main.layout_filter_menu.view.*


/**
 * File Description
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/11/2019
 * Project : TheMovie
 */
class FilterDialogFragment : AppCompatDialogFragment() {


    var year = arrayOf(
        "2000",
        "2001",
        "2002",
        "2003",
        "2004",
        "2005",
        "2006",
        "2007",
        "2008",
        "2009",
        "2010",
        "2011",
        "2012",
        "2013",
        "2014",
        "2015",
        "2016",
        "2017",
        "2018",
        "2019"
    )


    var selectedFromYear: String = "";
    var selectedToYear: String = "";


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_filter_menu, container, false)

        val spinnerArrayAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, year)

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        view.fromYear.adapter = spinnerArrayAdapter
        view.toYear.adapter = spinnerArrayAdapter
        view.fromYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedFromYear = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        view.toYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedToYear = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        view.cancelButton.setOnClickListener {
            sendResult(Constants.RESULT_CODE_CANCEL)
            this.dismiss()
        }
        view.okButton.setOnClickListener {
            sendResult(Constants.RESULT_CODE_OK)
            this.dismiss()
        }

        return view

    }

    private fun sendResult(resultCode: Int) {
        val intent = Intent()
        intent.putExtra(Constants.FROM_YEAR, selectedFromYear)
        intent.putExtra(Constants.TO_YEAR, selectedToYear)

        targetFragment!!.onActivityResult(
            targetRequestCode, resultCode, intent
        )
    }
}