package com.kilani.nowornever.data.model

import com.kilani.nowornever.data.enums.ChallengeCategory
import com.kilani.nowornever.data.enums.ChallengeStatus

data class Challenge (
    var points: Int? = 0,
    var category: ChallengeCategory? = ChallengeCategory.RANDOM,
    var description: String? = "",
    var sender: String? = "",
    var receiver: String? = "",
    var status: ChallengeStatus? = ChallengeStatus.PROPOSED
)