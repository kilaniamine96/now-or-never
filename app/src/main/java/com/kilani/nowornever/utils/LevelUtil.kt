package com.kilani.nowornever.utils

import com.kilani.nowornever.data.enums.Level

object LevelUtil {
    fun getLevelFromScore(score: Int): Level {
        return when(score) {
            in 1..10 -> Level.CANARI
            in 10..20 -> Level.PIVERT
            in 20..30 -> Level.CANARD
            in 30..40 -> Level.PELICAN
            in 40..50 -> Level.GOELAND
            in 50..60 -> Level.PAON
            in 60..70 -> Level.FAUCON
            in 70..90 -> Level.AIGLE
            in 90..Int.MAX_VALUE -> Level.CONDOR
            else -> Level.CANARI
        }
    }
}