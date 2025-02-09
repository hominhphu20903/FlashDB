package com.phuhm.flashalertdb.screen.light

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.phuhm.flashalertdb.data.model.Flash
import com.phuhm.flashalertdb.data.model.FlashType
import com.phuhm.flashalertdb.data.repository.FlashRepositoryImpl
import com.phuhm.flashalertdb.shared.MyApplication
import kotlinx.coroutines.launch

class FlashLightViewModel(private val flashRepositoryImpl: FlashRepositoryImpl) : ViewModel() {
    fun getFlashTypeLight() : Flash? {
        return flashRepositoryImpl.getFlashByType(FlashType.LIGHT)
    }

    fun updateFlashTypeLight(flash: Flash) {
        viewModelScope.launch {
            flashRepositoryImpl.insertFlash(flash)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val flashRepositoryImpl = (this[APPLICATION_KEY] as MyApplication).flashRepositoryImpl
                FlashLightViewModel(flashRepositoryImpl = flashRepositoryImpl)
            }
        }
    }
}