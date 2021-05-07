package com.example.sixtv.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.sixtv.databinding.ActivityMainBinding
import com.example.sixtv.detail.MenuDetailsActivity
import com.example.sixtv.ext.load
import com.example.sixtv.ext.save
import com.example.sixtv.ext.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy {
        MainAdapter { tvId ->
            MenuDetailsActivity.start(this, tvId)
            SP_NAME.save(this, mapOf(SP_KEY to tvId), clear = true, now = true)
        }
    }

    companion object {
        private const val SP_NAME = "last_selected_menu"
        private const val SP_KEY = "menu_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initObs()
        onRefresh()
        checkHistory()
    }

    private fun initView() {
        binding.recyclerElements.adapter = adapter
        binding.recyclerElements.addItemDecoration(MainItemDecoration())
    }

    private fun initObs() {
        // 监听状态
        viewModel.mResultStatusObs.observe(this, {
            toast(it)
        })
        // 填充结果
        viewModel.mResultContentObs.observe(this, {
            adapter.setData(it)
        })
    }

    private fun onRefresh() {
        viewModel.getTvs()
    }

    private fun checkHistory() {
        SP_NAME.load(this)[SP_KEY]?.takeIf {
            it is String
        }?.let {
            val tvId = it as String
            MenuDetailsActivity.start(this, tvId)
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

    // endregion

    // region 退出逻辑

    private var back = false

    /**
     * 两下退出App
     */
    override fun onBackPressed() {
        if (!back) {
            back = true
            lifecycleScope.launch(Dispatchers.IO) {
                delay(1500)
                back = false
            }
        } else {
            super.onBackPressed()
        }
    }

    // endregion
}