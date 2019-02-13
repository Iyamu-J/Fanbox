package space.fanbox.android.fanbox.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import space.fanbox.android.fanbox.R
import space.fanbox.android.fanbox.ui.DetailsActivity

class Presenter(private val context: Context) {

    fun onLetterClick(letterId: String) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(context.getString(R.string.extra_letter_id), letterId)
        context.startActivity(intent)

        Log.i(Presenter::class.java.simpleName, letterId)
    }
}