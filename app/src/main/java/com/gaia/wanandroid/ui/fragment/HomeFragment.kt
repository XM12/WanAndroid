package com.gaia.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gaia.wanandroid.adapter.HomeListAdapter
import com.gaia.wanandroid.base.BaseFragment
import com.gaia.wanandroid.bean.BannerBean
import com.gaia.wanandroid.databinding.FragmentHomeBinding
import com.gaia.wanandroid.viewmodel.HomeViewModel
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private var adapter: HomeListAdapter? = null

    override fun providerVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.swipeView.isEnabled = scrollY >= 0
        }
        binding.swipeView.setOnRefreshListener {
            viewModel.loadHomeListData()
        }
    }

    override fun initData() {
        super.initData()
        viewModel.getBinnerData()
        viewModel.loadHomeListData()
        viewModel.bannerData.observe(this) {
            binding.banner
                .setAdapter(object : BannerImageAdapter<BannerBean>(it) {
                    override fun onBindView(
                        holder: BannerImageHolder?,
                        data: BannerBean?,
                        position: Int,
                        size: Int
                    ) {
                        Glide.with(holder!!.itemView)
                            .load(data!!.imagePath)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                            .into(holder.imageView)
                    }
                })
                .setIndicator(CircleIndicator(activity))
                .addBannerLifecycleObserver(this@HomeFragment)
        }
        viewModel.homeData.observe(this) {
            adapter?.apply {
                if (binding.swipeView.isRefreshing) {
                    getData().clear()
                }
                notifyData(it.datas!!)
                binding.swipeView.isRefreshing = false
            } ?: apply {
                adapter = HomeListAdapter(activity!!, it.datas!!)
                adapter!!.setHasStableIds(true)
                binding.lv.adapter = adapter
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.banner.start()
    }

    override fun onStop() {
        super.onStop()
        binding.banner.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.banner.destroy()
    }
}