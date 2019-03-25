package com.example.traderevchallenge.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.traderevchallenge.R
import com.example.traderevchallenge.model.CuratedPhotoModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*


class PhotoAdapter(private val context: Context,private val photoList: ArrayList<CuratedPhotoModel>, val itemClickListener: ItemClickListener): BaseAdapter() {

    override fun getView(position: Int,convertview: View?, parent: ViewGroup?): View {
        var photo =photoList[position]

        var imgPhoto : ImageView
        var viewHolder : ViewHolder
        var convertView =convertview

        if(convertView == null){
            convertView=LayoutInflater.from(parent?.context).inflate(R.layout.item_photo, parent, false)

            imgPhoto = convertView.findViewById(R.id.img_photo)
            viewHolder = ViewHolder(imgPhoto,position)
            convertView.tag = viewHolder
        }

         viewHolder = convertView?.tag as ViewHolder

        val bitmapImage = BitmapFactory.decodeFile(photo.urls.thumb)

//        Log.d("droidTest","photo.urls.thumb :: "+photo.urls.thumb)
//        Log.d("droidTest","bitmapImage :: "+bitmapImage)
//        if(null !=bitmapImage) {
//            val nh = (bitmapImage.height * (512.0 / bitmapImage.width)).toInt()
//            val scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true)
//            viewHolder.imgPhoto.setImageBitmap(scaled)
//        }

        Picasso.with(context).load(photo.urls.thumb).into(viewHolder.imgPhoto)

        return convertView
    }

    override fun getItem(position: Int) = photoList[position]

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int = photoList.size

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    inner class ViewHolder(view: View,position: Int) : RecyclerView.ViewHolder(view),View.OnClickListener {

        val imgPhoto=view.img_photo
        val pos =position

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if (itemClickListener != null) {
                if (v != null) {
                    itemClickListener.onItemClick(v,pos )
                }
            }
        }
    }


}