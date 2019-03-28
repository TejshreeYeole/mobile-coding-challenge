package com.example.traderevchallenge.home

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import com.example.traderevchallenge.R
import com.example.traderevchallenge.Utils.AppConstants
import com.example.traderevchallenge.model.CuratedPhotoModel
import com.example.traderevchallenge.detail.PhotoDetailActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() , PhotoAdapter.ItemClickListener,HomeInterface.View {

    private var pageNo : Int = 1
    private var loadmore : Boolean =true

    private lateinit var photoAdapter : PhotoAdapter
    private var photoList : ArrayList<CuratedPhotoModel> = ArrayList()

    private var currentFirstVisibleItem: Int = 0
    private var currentVisibleItemCount: Int = 0
    private var currentScrollState: Int = 0

    private lateinit var mPresenter : HomeInterface.Presenter
    private lateinit var dialog :ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setList()
    }

    private fun isScrollCompleted() {
        if (this.currentVisibleItemCount > 0 && this.currentScrollState == SCROLL_STATE_IDLE) {
            if (!loadmore) {
                loadmore = true
                callApi()
            }
        }
    }

    private fun setList() {
        dialog = ProgressDialog(this@HomeActivity)
        mPresenter=HomePresenterImpl(this)

        photoList = ArrayList()
        photoAdapter = PhotoAdapter(this, photoList, this)
        gv_photos.adapter = photoAdapter

        gv_photos.setOnScrollListener(object: AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                currentFirstVisibleItem = firstVisibleItem
                currentVisibleItemCount = visibleItemCount
            }
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                currentScrollState = scrollState
                isScrollCompleted()
            }
        })
        callApi()
    }

    private fun callApi() {
        dialog.setMessage(getString(R.string.pleasewait))
        dialog.show()
        mPresenter.getPhotoList(pageNo)
    }

    override fun sendPhotoList(response: ArrayList<CuratedPhotoModel>) {
        dialog.dismiss()
        if(response !=null) {
            photoList.addAll(response)
            pageNo++
            photoAdapter.notifyDataSetChanged()
            loadmore=false
        }
    }

    override fun onItemClick(view: View, position: Int) {
        var intent = Intent(this, PhotoDetailActivity::class.java)
        val args = Bundle()

        args.putParcelableArrayList(AppConstants.PHOTOLIST, photoList)
        args.putInt(AppConstants.POSITION,position)
        intent.putExtra(AppConstants.BUNDLE, args)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === 1) {
            if (resultCode === 2) {
                val pos = data?.getIntExtra(AppConstants.POS,0)
                if (pos != null) {
                    gv_photos.setSelection(pos)
                }
            }
        }
    }
}
