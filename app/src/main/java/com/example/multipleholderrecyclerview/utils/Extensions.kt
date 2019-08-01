package com.example.multipleholderrecyclerview.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleholderrecyclerview.R
import com.example.multipleholderrecyclerview.TimeLineAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList


fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}
fun logt(msg:String) = Log.d("Test", msg)

@BindingAdapter("setAdapter")
fun setAdapter(view: RecyclerView, adapter: TimeLineAdapter){
    logt("here")
    view.adapter = adapter
}
@BindingAdapter("observeLiveData")
fun observe(view: View, liveData: LiveData<Boolean>){
    logt("here")
    view.parent?.let {
        view.getParentActivity()?.let {
            liveData.observe(it, Observer { value->
                (view as? FloatingActionButton)?.let{ fb->
                    if(value == true) fb.show() else fb.hide()
                    return@Observer
                }
                view.visibility = if(value == true)View.VISIBLE else View.GONE
            })
        }
    }
}
@BindingAdapter(value = ["anchorfb", "anchorfbEd"])
fun bindRecyclerViewWithFB(recyclerView: RecyclerView, anchorfb: FloatingActionButton, anchorfbEd: EditText) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0 && anchorfb.isShown) {
                anchorfb.hide()
            } else if (dy < 0 && !anchorfb.isShown && anchorfbEd.text.toString().isNotEmpty()) {
                anchorfb.show()
            }
        }
    })
}

fun showDialogImage(context: Context, image: Bitmap){
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    var dialog: AlertDialog? = null
    val view  = LayoutInflater.from(context).inflate(R.layout.dialog_image_show, null)
    view.findViewById<ImageView>(R.id.dialog_image).setImageBitmap(image)
    view.findViewById<ImageView>(R.id.dialog_close).setOnClickListener {
        dialog?.dismiss()
    }
    builder.setView(view)
    dialog = builder.show()
}
fun scaleDown(
    realImage: Bitmap,
    maxImageSize: Float,
    filter: Boolean
): Bitmap {
    val ratio = Math.min(
        maxImageSize / realImage.width,
        maxImageSize / realImage.height
    )
    val width = Math.round(ratio * realImage.width)
    val height = Math.round(ratio * realImage.height)

    return Bitmap.createScaledBitmap(
        realImage, width,
        height, filter
    )
}