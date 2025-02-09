package com.phuhm.flashalertdb.screen.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.flashalertdb.base.BaseActivity
import com.phuhm.flashalertdb.databinding.ActivityMainBinding
import com.phuhm.flashalertdb.screen.calls.FlashCallsActivity
import com.phuhm.flashalertdb.screen.light.FlashLightActivity
import com.phuhm.flashalertdb.screen.notification.FlashNotificationActivity
import com.phuhm.flashalertdb.screen.sms.FlashSmsActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        binding.btnFlashLight.setOnClickListener {
            val intent = Intent(this, FlashLightActivity::class.java)
            startActivity(intent)
        }

        binding.btnFlashCalls.setOnClickListener {
            val intent = Intent(this, FlashCallsActivity::class.java)
            startActivity(intent)
        }

        binding.btnFlashSMS.setOnClickListener {
            val intent = Intent(this, FlashSmsActivity::class.java)
            startActivity(intent)
        }

        binding.btnFlashNotification.setOnClickListener {
            val intent = Intent(this, FlashNotificationActivity::class.java)
            startActivity(intent)
        }
    }
}