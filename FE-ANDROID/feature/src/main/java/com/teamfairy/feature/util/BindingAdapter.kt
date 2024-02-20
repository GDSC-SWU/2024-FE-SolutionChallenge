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
import java.time.format.DateTimeParseException
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
    try {
        val formatter = DateTimeFormatter.ofPattern(context.getString(R.string.format_date_MMM_d), Locale.ENGLISH)
        val parsedDate = LocalDate.parse(date)
        text = parsedDate.format(formatter)
    } catch (e: DateTimeParseException) {
        // 파싱 실패 시 오늘 날짜의 해당 달과 받은 date 일로 결과를 보여줌
        val today = LocalDate.now()
        val resultDate = today.withDayOfMonth(date.toInt())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        text = resultDate.format(formatter)
    }
}

@BindingAdapter("formatDateyyyyMM")
fun TextView.formatDateyyyyMM(date: String) {
    try {
        val formatter =
            DateTimeFormatter.ofPattern(context.getString(R.string.format_date_yyyy_MM), Locale.ENGLISH)
        text = LocalDate.parse(date).format(formatter)
    } catch (e: DateTimeParseException) {
        // 파싱 실패 시 오늘 날짜의 해당 달과 받은 date 일로 결과를 보여줌
        val today = LocalDate.now()
        val resultDate = today.withDayOfMonth(date.toInt())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH)
        text = resultDate.format(formatter)
    }
}

@BindingAdapter(*["setExchangePrice", "setExchangeCountry"])
fun TextView.exchange(price: String, country: String) {
    val myFormatter = DecimalFormat("#,###")

    when (country) {
        "1" -> text = myFormatter.format(price.toDouble() * 0.0054).toInt().toString() + " ¥"
        "2" -> text = myFormatter.format(price.toDouble() * 18.38).toInt().toString() + " ₫"
    }
}

@BindingAdapter("changePriceFormant")
fun TextView.changePriceFormat(price: String) {
    val myFormatter = DecimalFormat("#,###")
    text = myFormatter.format(price.toDouble().toInt()).toString() + " ₩"
}
