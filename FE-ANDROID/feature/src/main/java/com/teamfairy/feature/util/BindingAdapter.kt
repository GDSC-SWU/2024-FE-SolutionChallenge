package com.teamfairy.feature.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.teamfairy.feature.R
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.load(url)
}

@BindingAdapter("setCircleImage")
fun ImageView.setCircleImage(img: String?) {
    load(img) {
        transformations(RoundedCornersTransformation(1000f))
    }
}

@BindingAdapter("formatDateMMMd")
fun TextView.formatDateMMMd(date: String) {
    val formatter =
        DateTimeFormatter.ofPattern(context.getString(R.string.format_date_MMM_d), Locale.ENGLISH)
    text = LocalDate.parse(date).format(formatter)
}

@BindingAdapter("formatDateyyyyMM")
fun TextView.formatDateyyyyMM(date: String) {
    val formatter =
        DateTimeFormatter.ofPattern(context.getString(R.string.format_date_yyyy_MM), Locale.ENGLISH)
    text = LocalDate.parse(date).format(formatter)
}

@BindingAdapter(*["setExchangePrice","setExchangeCountry"])
fun TextView.exchange(price: String, country: String) {
    val myFormatter = DecimalFormat("#,###")

    when (country) {
        "0" -> text = myFormatter.format(price.toInt() * 0.0054).toString() + "¥"
        "1" -> text = myFormatter.format(price.toInt() * 18.38).toString() + "₫"
    }
}
