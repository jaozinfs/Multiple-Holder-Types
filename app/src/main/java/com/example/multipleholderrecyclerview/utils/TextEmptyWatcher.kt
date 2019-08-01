package com.example.multipleholderrecyclerview.utils

import android.text.Editable
import android.text.TextWatcher

class TextEmptyWatcher(val callback: (Boolean)->Unit ) : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(p0?.toString()?.length == 0 || p0?.toString()!!.trim().isEmpty()) callback(false)
        else callback(true)
    }
}