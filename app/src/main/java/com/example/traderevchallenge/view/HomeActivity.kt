package com.example.traderevchallenge.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AbsListView
import com.example.traderevchallenge.R
import com.example.traderevchallenge.api.ApiHandler
import com.example.traderevchallenge.model.CuratedPhotoModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException


class HomeActivity : AppCompatActivity() , PhotoAdapter.ItemClickListener {

    private var pageNo : Int = 1
    private var myLastVisiblePos : Int = 0
    private var loadmore : Boolean =true


    private lateinit var photoAdapter : PhotoAdapter
    private var photoList : ArrayList<CuratedPhotoModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setList()

        myLastVisiblePos = gv_photos.firstVisiblePosition

        gv_photos.setOnScrollListener(object: AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                val lastItem = firstVisibleItem + visibleItemCount - 1

                if(lastItem >= totalItemCount -1){
                    if (loadmore) {
                        loadmore = false
                        callApi()
                    }
                }
            }
            override fun onScrollStateChanged(view: AbsListView?, state: Int) {
            }
        })
    }

    private fun setList() {
        photoList = ArrayList()
        photoAdapter = PhotoAdapter(this,photoList, this)
        gv_photos.adapter = photoAdapter

        callApi()
    }

    private fun callApi() {
        var dialog = ProgressDialog(this@HomeActivity)
        dialog.setMessage("Please Wait...")
        dialog.show()

        val service = ApiHandler.getApiService()
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getCuratedPhotos(pageNo,20,"latest"
                ,ApiHandler.ACCESS_TOKEN)
            try {
                val response: ArrayList<CuratedPhotoModel> = request.await()
                dialog.dismiss()
                if(response !=null) {
                    photoList.addAll(response)
                    photoAdapter.notifyDataSetChanged()
                    pageNo++
                    loadmore=true
                }else{
                }
            } catch (e: HttpException) {
            } catch (e: Throwable) {
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {
        var intent = Intent(this,PhotoDetailActivity::class.java)
        intent.putExtra("Bitmap",photoList[position].urls.full)
        startActivity(intent)
    }
}
