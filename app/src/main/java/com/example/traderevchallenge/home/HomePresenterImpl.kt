package com.example.traderevchallenge.home

import com.example.traderevchallenge.model.CuratedPhotoModel

class HomePresenterImpl(private val view: HomeActivity) : HomeInterface.Presenter{

    private var mInteractor:HomeInteractorImpl = HomeInteractorImpl(this)

    override fun getPhotoList(pageNo: Int) {
        mInteractor.getPhotoList(pageNo)
    }

    override fun sendPhotoList(response: ArrayList<CuratedPhotoModel>) {
        view.sendPhotoList(response)
    }


}