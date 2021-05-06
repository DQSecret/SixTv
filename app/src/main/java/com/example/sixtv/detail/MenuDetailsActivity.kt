package com.example.sixtv.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.sixtv.databinding.ActivityMenuDetailsBinding
import com.example.sixtv.ext.px2dp
import com.example.sixtv.ext.screenSizePoint

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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initParams()
        initView()
        initObs()
        onRefresh()

        // 添加宽高的显示
        val w = screenSizePoint.x.px2dp
        val h = screenSizePoint.y.px2dp
        binding.tvDimen.text = "$w x $h"
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
            // toast(it)
        })
        // 填充结果
        viewModel.mResultContentObs.observe(this, {
            adapter.setData(it)
        })
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

    private fun onRefresh() {
        viewModel.getMenus(tvId)
    }
}