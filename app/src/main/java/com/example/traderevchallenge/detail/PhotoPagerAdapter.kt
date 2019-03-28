package com.example.traderevchallenge.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.traderevchallenge.model.CuratedPhotoModel
import android.os.Bundle
import com.example.traderevchallenge.Utils.AppConstants


class PhotoPagerAdapter(fm: FragmentManager, private val photolist: ArrayList<CuratedPhotoModel>) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = photolist.size

    override fun getItem(position: Int): Fragment {
        val fragment = PhotoPageFragment()
        val bundle = Bundle()
        bundle.putString(AppConstants.URL, photolist[position].urls.thumb)
        bundle.putString(AppConstants.NAME,photolist[position].user.name)
        bundle.putString(AppConstants.DESCRIPTION,photolist[position].description)
        bundle.putInt(AppConstants.POSITION,position)
        fragment.arguments = bundle
        return fragment
    }
}