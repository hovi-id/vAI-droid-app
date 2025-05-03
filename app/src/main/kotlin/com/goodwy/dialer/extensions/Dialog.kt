package com.goodwy.dialer.extensions

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Shows a simple info dialog with title, message and OK button
 * @param title Dialog title text
 * @param message Dialog message text
 * @param positiveRes String resource for positive button (defaults to android.R.string.ok)
 */
fun Context.showInfoDialog(
    title: String,
    message: String,
    @StringRes positiveRes: Int = android.R.string.ok
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveRes) { dialog, _ -> dialog.dismiss() }
        .show()
}

/**
 * Fragment version that safely handles null activity
 */
fun Fragment.showInfoDialog(
    title: String,
    message: String,
    @StringRes positiveRes: Int = android.R.string.ok
) {
    activity?.let { context ->
        context.showInfoDialog(title, message, positiveRes)
    }
}
