package com.phuhm.flashalertdb.screen.light

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.activity.viewModels
import com.phuhm.flashalertdb.base.BaseActivity
import com.phuhm.flashalertdb.data.model.Flash
import com.phuhm.flashalertdb.databinding.ActivityFlashLightBinding
import com.phuhm.flashalertdb.utils.TimeUtils

class FlashLightActivity : BaseActivity<ActivityFlashLightBinding>() {
    private val flashLightViewModel: FlashLightViewModel by viewModels { FlashLightViewModel.Factory }
    private var flashTypeLight: Flash? = null

    companion object {
        private const val MAX_TIME_FLASH = 2000
    }

    override fun inflateBinding(inflater: LayoutInflater): ActivityFlashLightBinding {
        return ActivityFlashLightBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initViews()
        handleEvents()
    }

    private fun initData() {
        flashTypeLight = flashLightViewModel.getFlashTypeLight()
    }

    private fun initViews() {
        initSeekbars()
        setTimeSeekbars()
    }

    private fun initSeekbars() {
        binding.sbTimeOn.max = MAX_TIME_FLASH
        binding.sbTimeOff.max = MAX_TIME_FLASH
    }

    private fun setTimeSeekbars() {
        flashTypeLight?.let { flash ->
            binding.sbTimeOn.progress = flash.timeOn.toInt()
            binding.sbTimeOff.progress = flash.timeOff.toInt()
            binding.tvTimeOnFlash.text = TimeUtils.formatTime(flash.timeOn)
            binding.tvTimeOffFlash.text = TimeUtils.formatTime(flash.timeOff)
        }
    }

    private fun handleEvents() {
        binding.btnTest.setOnClickListener {
            Log.d("00000000000000", "handleEvents: $flashTypeLight")
        }

        binding.sbTimeOn.setOnSeekBarChangeListener(onChangeTimeOnFlash())
        binding.sbTimeOff.setOnSeekBarChangeListener(onChangeTimeOffFlash())
    }

    private fun onChangeTimeOnFlash() = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val selectedTime = seekBar?.progress?.toLong() ?: 0L
            binding.tvTimeOnFlash.text = TimeUtils.formatTime(selectedTime)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            val selectedTime = seekBar?.progress?.toLong() ?: 0L
            flashTypeLight?.timeOn = selectedTime
            updateFlashTypeLight()
        }
    }

    private fun onChangeTimeOffFlash() = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val selectedTime = seekBar?.progress?.toLong() ?: 0L
            binding.tvTimeOffFlash.text = TimeUtils.formatTime(selectedTime)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            val selectedTime = seekBar?.progress?.toLong() ?: 0L
            flashTypeLight?.timeOff = selectedTime
            updateFlashTypeLight()
        }
    }

    private fun updateFlashTypeLight() {
        flashTypeLight?.let { flash ->
            flashLightViewModel.updateFlashTypeLight(flash)
        }
    }
}