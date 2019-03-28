package com.example.traderevchallenge.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.traderevchallenge.R
import com.example.traderevchallenge.model.CuratedPhotoModel
import kotlinx.android.synthetic.main.activity_photo_detail.*
import android.content.Intent
import com.example.traderevchallenge.Utils.AppConstants

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        val args = intent.getBundleExtra(AppConstants.BUNDLE)
        val list = args.getSerializable(AppConstants.PHOTOLIST) as ArrayList<CuratedPhotoModel>

        val position = args.getInt(AppConstants.POSITION)
        val pagerAdapter = PhotoPagerAdapter(supportFragmentManager, list)
        pager.adapter = pagerAdapter
        pager.currentItem=position
    }

    override fun onBackPressed() {
        val data = pager.currentItem
        val intent = Intent()
        intent.putExtra(AppConstants.POS, data)
        setResult(2, intent)
        finish()
    }

}
