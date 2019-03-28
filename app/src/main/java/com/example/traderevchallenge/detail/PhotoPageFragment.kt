package com.example.traderevchallenge.detail

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traderevchallenge.R
import com.example.traderevchallenge.Utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_photodetail.*


class PhotoPageFragment : Fragment() {

    private var url : String?=null
    private var name : String?=null
    private var description : String?=null
    private var position : Int? =0
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        url = arguments?.getString(AppConstants.URL)
        name = arguments?.getString(AppConstants.NAME)
        description = arguments?.getString(AppConstants.DESCRIPTION)
        position = arguments?.getInt(AppConstants.POSITION)
        val view= inflater.inflate(R.layout.fragment_photodetail, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tv_name.text=name
        Picasso.with(requireContext()).load(url).into(img_photo)
        if(null !=description) {
            tv_description.text = description
            tv_description.visibility = View.VISIBLE
        }
        else {
            tv_description.visibility = View.GONE
        }
    }
}
