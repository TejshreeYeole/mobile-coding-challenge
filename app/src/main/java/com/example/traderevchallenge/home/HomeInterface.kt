package com.example.traderevchallenge.home

import com.example.traderevchallenge.model.CuratedPhotoModel

interface HomeInterface {

    interface View{
        fun sendPhotoList(response: ArrayList<CuratedPhotoModel>)
    }

    interface Presenter{
        fun getPhotoList(pageNo: Int)
        fun sendPhotoList(response: ArrayList<CuratedPhotoModel>)

    }

    interface Interactor{
        fun getPhotoList(pageNo: Int)

    }
}