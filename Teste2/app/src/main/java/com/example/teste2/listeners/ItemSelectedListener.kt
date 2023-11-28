package com.example.teste2.listeners

import android.view.View
import android.widget.AdapterView

class ItemSelectedListener : AdapterView.OnItemSelectedListener {

    val function : (result : Int) -> Unit

    constructor(function : (result : Int) -> Unit) {
        this.function = function
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        function(p2)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}