package com.example.traderevchallenge.home

import com.example.traderevchallenge.Utils.api.ApiHandler
import com.example.traderevchallenge.model.CuratedPhotoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeInteractorImpl(private var presenter: HomePresenterImpl) : HomeInterface.Interactor{

    override fun getPhotoList(pageNo: Int) {
        val service = ApiHandler.getApiService()
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getCuratedPhotos(pageNo,
                10,"latest", ApiHandler.ACCESS_TOKEN)
            try {
                val response: ArrayList<CuratedPhotoModel> = request.await()
                presenter.sendPhotoList(response)
            } catch (e: HttpException) {
            } catch (e: Throwable) {
            }
        }
    }
}