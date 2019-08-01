package com.example.multipleholderrecyclerview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleholderrecyclerview.holder.FriendlyType
import com.example.multipleholderrecyclerview.holder.ITimeLineViewHolder
import com.example.multipleholderrecyclerview.timeline.Timeline
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.multipleholderrecyclerview.holder.ImageType
import com.example.multipleholderrecyclerview.holder.TextType
import com.example.multipleholderrecyclerview.timeline.Friendly
import com.example.multipleholderrecyclerview.timeline.Image
import com.example.multipleholderrecyclerview.timeline.Text
import com.example.multipleholderrecyclerview.utils.getParentActivity
import com.example.multipleholderrecyclerview.utils.scaleDown
import com.example.multipleholderrecyclerview.utils.showDialogImage


class TimeLineAdapter : ListAdapter<Timeline, RecyclerView.ViewHolder>(TimeLineDiffUtils){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            Text -> { TextViewHoler(TextType().getHolder(parent))
            }
            Friendly->{ FriendlyViewHolder(FriendlyType().getHolder(parent)) }
            else->{ ImageHolder(ImageType().getHolder(parent))}
        }
    }
    override fun getItemViewType(position: Int): Int = getItem(position).getType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItem(position).getType()){
            Text-> { (holder as? TextViewHoler)?.let { holder.bindView(position) } }
            Friendly-> { (holder as? FriendlyViewHolder)?.let { holder.bindView(position) } }
            Image->{ (holder as? ImageHolder)?.let { holder.bindView(position) }}
        }
    }

    inner class FriendlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ITimeLineViewHolder{
        override fun bindView(position: Int) {}
    }
    inner class TextViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView), ITimeLineViewHolder{
        private val tv = itemView.findViewById<TextView>(R.id.textView)

        override fun bindView(position: Int) {
            tv.text = (getItem(position) as TextType).text
        }
    }
    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ITimeLineViewHolder{
        private val imgv = itemView.findViewById<ImageView>(R.id.imageView)

        override fun bindView(position: Int) {
            val d = (ContextCompat.getDrawable(itemView.context, R.drawable.images) as BitmapDrawable)
            val bt = scaleDown( d.bitmap,
                Resources.getSystem().displayMetrics.widthPixels.toFloat(), true)
            imgv.setOnClickListener {
                showDialogImage(itemView.context, bt)
            }
            imgv.setImageBitmap(bt)
        }
    }
    object TimeLineDiffUtils: DiffUtil.ItemCallback<Timeline>(){
        override fun areItemsTheSame(oldItem: Timeline, newItem: Timeline): Boolean = oldItem.getId() == newItem.getId()
        override fun areContentsTheSame(oldItem: Timeline, newItem: Timeline): Boolean = oldItem.equals( newItem )
    }

}