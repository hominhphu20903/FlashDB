package com.phuhm.flashalertdb.screen.calls

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.phuhm.flashalertdb.base.BaseActivity
import com.phuhm.flashalertdb.databinding.ActivityFlashCallsBinding

class FlashCallsActivity : BaseActivity<ActivityFlashCallsBinding>() {
    private var isShowImageWarningTop: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            try {
                isShowImageWarningTop = !isShowImageWarningTop
                if(isShowImageWarningTop) {
                    binding.imvWarningTop.visibility = View.VISIBLE
                    binding.imvWarningBottom.visibility = View.INVISIBLE
                } else {
                    binding.imvWarningTop.visibility = View.INVISIBLE
                    binding.imvWarningBottom.visibility = View.VISIBLE
                }
                handler.postDelayed(this, 500)
            } catch (e: Exception) {
                Log.d("FlashCallsActivity", "run: ")
            }
        }
    }
    override fun inflateBinding(inflater: LayoutInflater): ActivityFlashCallsBinding {
        return ActivityFlashCallsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}