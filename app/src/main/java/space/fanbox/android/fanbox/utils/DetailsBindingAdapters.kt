package space.fanbox.android.fanbox.utils

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import space.fanbox.android.fanbox.BuildConfig
import space.fanbox.android.fanbox.R
import space.fanbox.android.fanbox.glide.GlideApp
import space.fanbox.android.fanbox.model.Tag
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@BindingAdapter("formatDetailsDate")
fun setDetailsFormattedDate(view: TextView, date: String?) {

    if (date != null) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val dateCreated = simpleDateFormat.parse(date)
        val dateFormat = SimpleDateFormat.getDateInstance()
        view.text = dateFormat.format(dateCreated) ?: ""
    }
}

@BindingAdapter("showCard")
fun showCard(view: CardView, attachment: String?) {
    view.visibility = if (attachment.isNullOrEmpty()) GONE else VISIBLE
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, attachment: String?) {
    if (attachment != null) {
        val isPhoto: Boolean = attachment.isNotEmpty()
        if (isPhoto) {
            val url = BuildConfig.PHOTO_URL + attachment
            view.visibility = VISIBLE
            GlideApp.with(view.context)
                .load(url)
                .error(R.drawable.ic_broken_image)
                .into(view)
        } else {
            view.visibility = GONE
        }
    }
}

@BindingAdapter("chipDetailsCategory")
fun setCategoryChip(view: ChipGroup, category: String?) {

    if (category != null) {
        val chip = Chip(view.context)
        chip.text = category
        view.addView(chip)
    }
}

@BindingAdapter("chipDetailsTags")
fun setTagChips(view: ChipGroup, tags: List<Tag>?) {

    if (tags != null) {
        for (tag in tags) {
            val chip = Chip(view.context)
            chip.text = tag.label
            chip.setTextColor(ColorStateList.valueOf(Color.parseColor("#${tag.text_color}")))
            chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#${tag.color}"))
            view.addView(chip)
        }
    }
}