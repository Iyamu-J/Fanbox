package space.fanbox.android.fanbox.listenerbindings

import android.content.Context
import android.content.Intent
import space.fanbox.android.fanbox.model.Letter

class DetailsPresenter(private val context: Context) {

    fun onShareClick(letter: Letter) {
        val openingString =
            "Read this letter ${letter.subject} from ${letter.sender_name} to ${letter.recipient} on FanBox! "
        val letterLink = "https://fanbox.space/letters/${letter.slug}"
        val shareMessage = openingString + letterLink
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = "text/plain"
        }
        context.startActivity(intent)
    }
}