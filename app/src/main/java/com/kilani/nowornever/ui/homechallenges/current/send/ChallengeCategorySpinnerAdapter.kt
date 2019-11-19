package com.kilani.nowornever.ui.homechallenges.current.send

import android.content.Context
import android.widget.ArrayAdapter
import com.kilani.nowornever.data.enums.ChallengeCategory
import com.kilani.nowornever.data.stringRes

class ChallengeCategorySpinnerAdapter  constructor(
    context: Context,
    simpleItemLayout: Int,
    challengeCategoryList: Array<ChallengeCategory>,
    private val hint: String
) : ArrayAdapter<ChallengeCategorySpinnerAdapter.ChallengeCategoryWithHint>(context, simpleItemLayout, arrayListOf()) {


    init {
        add(ChallengeCategoryWithHint())
        challengeCategoryList.forEach{ value ->
            add(ChallengeCategoryWithHint(value))
        }
    }

    override fun isEnabled(position: Int): Boolean = position != 0

    inner class ChallengeCategoryWithHint(val challengeCategory: ChallengeCategory? = null) {
        override fun toString() = challengeCategory?.let { context.getString(it.stringRes()) } ?: hint
    }
}