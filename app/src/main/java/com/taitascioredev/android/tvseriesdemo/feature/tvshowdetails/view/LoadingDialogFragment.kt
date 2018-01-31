package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.taitascioredev.android.tvseriesdemo.R

/**
 * Created by rrtatasciore on 27/01/18.
 */
class LoadingDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = ProgressDialog(context)
        dialog.isIndeterminate = true
        dialog.setMessage(context.getString(R.string.loading))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }
}