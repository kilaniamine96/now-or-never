package com.kilani.nowornever.ui.homechallenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kilani.nowornever.R
import kotlinx.android.synthetic.main.dialog_confirm_choice.*

class ConfirmationDialogFragment : DialogFragment() {

    interface ConfirmationDialogListener {
        fun onConfirm()
        fun onCancel()
    }

    companion object {
        fun newInstance() =
            ConfirmationDialogFragment()
    }

    var listener: ConfirmationDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_confirm_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        cancelBtn.setOnClickListener { listener?.onCancel() }
        sendBtn.setOnClickListener { listener?.onConfirm() }
    }
}