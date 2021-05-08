package com.example.sixtv.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.sixtv.BuildConfig
import com.example.sixtv.databinding.ActivityMenuDetailsBinding
import com.example.sixtv.ext.px2dp
import com.example.sixtv.ext.screenSizePoint
import com.example.sixtv.ext.toast

class MenuDetailsActivity : FragmentActivity() {

    private val binding by lazy { ActivityMenuDetailsBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MenuDetailsViewModel>()

    private lateinit var tvId: String
    private val adapter by lazy { MenuDetailAdapter() }

    companion object {

        fun start(context: Context, tvId: String) {
            Intent(context, MenuDetailsActivity::class.java).apply {
                putExtra("tvId", tvId)
            }.let {
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initParams()
        initView()
        initObs()
        onRefresh()
        showDeviceDimen()
    }

    private fun initParams() {
        tvId = intent.getStringExtra("tvId") ?: "1"
    }

    private fun initView() {
        binding.recyclerElements.adapter = adapter
    }

    private fun initObs() {
        // 监听状态
        viewModel.mResultStatusObs.observe(this, {
            toast(it)
        })
        // 填充结果
        viewModel.mResultContentObs.observe(this, {
            binding.viewMenuTitle.bindData(it.tv)
            adapter.setData(it.menu)
        })
    }

    private fun onRefresh() {
        viewModel.getMenus(tvId)
    }

    /**
     * 显示屏幕宽高
     */
    @SuppressLint("SetTextI18n")
    private fun showDeviceDimen() {
        if (BuildConfig.DEBUG) {
            val w = screenSizePoint.x.px2dp
            val h = screenSizePoint.y.px2dp
            binding.tvDimen.text = "$w x $h"
            binding.tvDimen.visibility = View.VISIBLE
        }
    }

    // region 刷新逻辑

    /**
     * 监听菜单键 - 刷新数据
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_MENU -> {
                onRefresh()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}