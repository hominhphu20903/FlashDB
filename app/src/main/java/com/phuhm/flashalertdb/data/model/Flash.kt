package com.phuhm.flashalertdb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flash(
    @PrimaryKey
    val type: FlashType,
    var timeOn: Long,
    var timeOff: Long
)