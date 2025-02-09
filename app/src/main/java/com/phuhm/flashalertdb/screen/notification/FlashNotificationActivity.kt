package com.phuhm.flashalertdb.screen.notification

import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.flashalertdb.base.BaseActivity
import com.phuhm.flashalertdb.databinding.ActivityFlashNotificationBinding

class FlashNotificationActivity : BaseActivity<ActivityFlashNotificationBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityFlashNotificationBinding {
        return ActivityFlashNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}