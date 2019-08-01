package com.example.multipleholderrecyclerview.timeline

import android.content.Context
import android.view.View
import android.view.ViewGroup


const val Photo = 1
const val Text = 2
const val Friendly = 3
const val Image = 4

interface Timeline{
    fun getId():Int
    fun getType():Int
    fun getHolder(parent: ViewGroup):View
}