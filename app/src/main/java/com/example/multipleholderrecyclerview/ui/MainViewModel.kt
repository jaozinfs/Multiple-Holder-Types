package com.example.multipleholderrecyclerview.ui

import android.app.Application
import android.content.Context
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.multipleholderrecyclerview.App
import com.example.multipleholderrecyclerview.R
import com.example.multipleholderrecyclerview.TimeLineAdapter
import com.example.multipleholderrecyclerview.holder.FriendlyType
import com.example.multipleholderrecyclerview.holder.ImageType
import com.example.multipleholderrecyclerview.holder.TextType
import com.example.multipleholderrecyclerview.timeline.Timeline

class MainViewModel(application: Application) : AndroidViewModel(application){
    private var app: Application = application

    private val timeLineAdapter = TimeLineAdapter()
    val _visibility = MutableLiveData<Boolean>()
    fun getVisibility() = _visibility
    val _sendPostVisibilty = MutableLiveData<Boolean>()
    fun getSendPostVisibility() = _sendPostVisibilty

    fun getTimeLineAdapter() = timeLineAdapter
    private val list = ArrayList<Timeline>()

    init {
        initList()
        setVisibility(true)
    }

    fun setVisibility(b: Boolean) {
        _visibility.value = b
    }

    private fun initList(){

        val p1 = TextType()
        val p2 = TextType()
        val p3 = TextType()
        val p4 = ImageType()
        p1.text = app.applicationContext.getString(R.string.loreip)
        p2.text = app.applicationContext.getString(R.string.loreip2)
        p3.text = app.applicationContext.getString(R.string.loreip)

        list.add(p1)
        list.add(p2)
        list.add(FriendlyType())
        list.add(p3)
        list.add(p4)


        timeLineAdapter.submitList(list)
        timeLineAdapter.notifyDataSetChanged()
    }

    fun addMessage(message:String, onadded: ()->Unit){
        val p = TextType()
        p.text = message
        list.add(p)
        timeLineAdapter.notifyDataSetChanged()
        onadded()
    }

}

