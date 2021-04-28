package com.example.sixtv

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.sixtv.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initObs()
    }

    private fun initObs() {
        viewModel.logs.observe(this, {
            binding.tvLog.text = it
        })
    }

    private fun initView() {
        binding.btn1.setOnClickListener { toastClick(it as Button) }
        binding.btn2.setOnClickListener { toastClick(it as Button) }
        binding.btn3.setOnClickListener { toastClick(it as Button) }
        binding.btn4.setOnClickListener { toastClick(it as Button) }
        binding.btn5.setOnClickListener { toastClick(it as Button) }
        binding.btn6.setOnClickListener { toastClick(it as Button) }
        binding.btn1.setOnLongClickListener { toastLongClick(it as Button) }
        binding.btn2.setOnLongClickListener { toastLongClick(it as Button) }
        binding.btn3.setOnLongClickListener { toastLongClick(it as Button) }
        binding.btn4.setOnLongClickListener { toastLongClick(it as Button) }
        binding.btn5.setOnLongClickListener { toastLongClick(it as Button) }
        binding.btn6.setOnLongClickListener { toastLongClick(it as Button) }
    }

    private fun addLog(log: String) = viewModel.addLog(log)

    private fun toastClick(btn: Button) {
        addLog("单击了-${btn.text}")
    }

    private fun toastLongClick(btn: Button): Boolean {
        addLog("长按了-${btn.text}")
        return true
    }

    private var back = false

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_ENTER, KEYCODE_DPAD_CENTER -> {
                addLog("点击了确认键")
            }
            KEYCODE_BACK -> {
                addLog("点击了返回键")
            }
            KEYCODE_DPAD_UP -> {
                addLog("点击了上键")
            }
            KEYCODE_DPAD_DOWN -> {
                addLog("点击了下键")
            }
            KEYCODE_DPAD_LEFT -> {
                addLog("点击了左键")
            }
            KEYCODE_DPAD_RIGHT -> {
                addLog("点击了右键")
            }
            KEYCODE_SETTINGS -> {
                addLog("点击了设置键")
            }
            KEYCODE_INFO -> {
                addLog("点击了INFO键")
            }
            KEYCODE_MENU -> {
                // TODO : 刷新页面
                addLog("点击了MENU键")
            }
            else -> {
                addLog("点击了${keyCode}键")
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}

class MainViewModel : ViewModel() {

    val logs: MutableLiveData<String> = MutableLiveData("按键日志:")
    private var step = 1

    fun addLog(log: String) {
        logs.value = "${logs.value} ${step++}.${log} ->"
    }
}