package com.example.multipleholderrecyclerview.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.multipleholderrecyclerview.R
import com.example.multipleholderrecyclerview.timeline.Friendly
import com.example.multipleholderrecyclerview.timeline.Timeline

class FriendlyType : Timeline{
    override fun getId(): Int = 2

    override fun getHolder(parent: ViewGroup): View =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_friendly_type, parent, false)

    override fun getType(): Int  = Friendly
}