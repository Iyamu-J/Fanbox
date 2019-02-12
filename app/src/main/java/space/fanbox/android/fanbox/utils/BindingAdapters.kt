package space.fanbox.android.fanbox.utils

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import space.fanbox.android.fanbox.model.Tag
import java.text.SimpleDateFormat

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.text = value ?: ""
        })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("chipTag")
fun setTagChips(view: ChipGroup, tags: MutableLiveData<List<Tag>>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && tags != null) {
        tags.observe(parentActivity, Observer { value ->
            // this is called to prevent multiple similar chips
            view.removeAllViews()

            for (tag in value) {
                val chip = Chip(parentActivity)
                chip.text = tag.label
                chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#${tag.color}"))
                chip.setTextColor(Color.parseColor("#${tag.text_color}"))
                view.addView(chip)
            }
        })
    }
}

@BindingAdapter("chipCategory")
fun setCategoryChip(view: ChipGroup, category: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && category != null) {
        category.observe(parentActivity, Observer { value ->
            // this is called to prevent multiple similar chips
            view.removeAllViews()

            val chip = Chip(parentActivity)
            chip.text = value ?: ""
            view.addView(chip)
        })
    }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("formatDate")
fun setFormattedDate(view: TextView, date: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && date != null) {
        date.observe(parentActivity, Observer { value ->
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val dateCreated = simpleDateFormat.parse(value)
            val dateFormat = SimpleDateFormat.getDateInstance()
            view.text = dateFormat.format(dateCreated) ?: ""
        })
    }
}