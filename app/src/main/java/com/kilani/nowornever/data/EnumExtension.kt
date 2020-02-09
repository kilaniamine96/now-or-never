package com.kilani.nowornever.data

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.kilani.nowornever.R
import com.kilani.nowornever.data.enums.ChallengeCategory

//ChallengeCategory
@StringRes
fun ChallengeCategory.stringRes(): Int {
    return when(this) {
        ChallengeCategory.LOVE -> R.string.love
        ChallengeCategory.HOT -> R.string.hot
        ChallengeCategory.FUNNY -> R.string.funny
        ChallengeCategory.RANDOM -> R.string.random
    }
}

@ColorRes
fun ChallengeCategory.colorRes(): Int {
    return when(this) {
        ChallengeCategory.LOVE -> R.color.raspberry
        ChallengeCategory.HOT -> R.color.red
        ChallengeCategory.FUNNY -> R.color.grass
        ChallengeCategory.RANDOM -> R.color.midnight_sun
    }
}