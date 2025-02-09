package com.phuhm.flashalertdb.screen.sms

import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.flashalertdb.base.BaseActivity
import com.phuhm.flashalertdb.databinding.ActivityFlashSmsBinding

class FlashSmsActivity : BaseActivity<ActivityFlashSmsBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityFlashSmsBinding {
        return ActivityFlashSmsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}