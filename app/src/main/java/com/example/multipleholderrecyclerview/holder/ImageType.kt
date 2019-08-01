package com.example.multipleholderrecyclerview.holder

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.multipleholderrecyclerview.R
import com.example.multipleholderrecyclerview.timeline.Friendly
import com.example.multipleholderrecyclerview.timeline.Image
import com.example.multipleholderrecyclerview.timeline.Text
import com.example.multipleholderrecyclerview.timeline.Timeline

class ImageType() : Timeline{
    override fun getId(): Int = 1

    override fun getHolder(parent: ViewGroup): View =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_image_type, parent, false)

    override fun getType(): Int  = Image

    lateinit var image: Drawable

}