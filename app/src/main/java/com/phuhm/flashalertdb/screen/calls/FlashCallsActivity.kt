package com.phuhm.flashalertdb.screen.calls

import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.flashalertdb.base.BaseActivity
import com.phuhm.flashalertdb.databinding.ActivityFlashCallsBinding

class FlashCallsActivity : BaseActivity<ActivityFlashCallsBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityFlashCallsBinding {
        return ActivityFlashCallsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}