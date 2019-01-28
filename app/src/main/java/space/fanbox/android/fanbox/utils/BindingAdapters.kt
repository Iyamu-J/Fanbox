package space.fanbox.android.fanbox.utils

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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("chips")
fun setChips(view: ChipGroup, tags: MutableLiveData<List<Tag>>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && tags != null) {
        tags.observe(parentActivity, Observer {
            value ->
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