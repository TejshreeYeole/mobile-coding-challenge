package com.example.traderevchallenge.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.traderevchallenge.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.*

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_photo)

        var imageFullUrl : String = intent.extras.getString("Bitmap")

       /* val bitmapImage = BitmapFactory.decodeFile(imageFullUrl)
        val nh = (bitmapImage.height * (512.0 / bitmapImage.width)).toInt()
        val scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true)
        img_photo.setImageBitmap(scaled)*/

        Picasso.with(this).load(imageFullUrl).into(img_photo)

    }
}
