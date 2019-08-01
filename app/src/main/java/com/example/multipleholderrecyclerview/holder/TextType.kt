package com.example.multipleholderrecyclerview.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.multipleholderrecyclerview.R
import com.example.multipleholderrecyclerview.timeline.Friendly
import com.example.multipleholderrecyclerview.timeline.Text
import com.example.multipleholderrecyclerview.timeline.Timeline

class TextType() : Timeline{
    override fun getId(): Int = 1

    override fun getHolder(parent: ViewGroup): View =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_text_type, parent, false)

    override fun getType(): Int  = Text

    lateinit var text: String

}