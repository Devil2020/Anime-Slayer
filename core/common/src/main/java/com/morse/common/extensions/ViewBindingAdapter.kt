package com.expertapps.base.extensions

import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.expertapps.base.extensions.*
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("setDate")
fun setDate(textView: AppCompatTextView, date: String? = null) {
    textView.text = date?.convertDate(textView.resources.configuration.locale) ?: "".convertDate(
        textView.resources.configuration.locale
    )
}

// انا لو بعمل load اخفى ال bar بتاعي
@BindingAdapter("manageViewVisibilty")
fun manageView(group: ConstraintLayout, isShown: Boolean) {
    when (isShown) {
        true -> {
            group.showVisibilty()
        }

        false -> {
            group.hideVisibilty()
        }
    }
}

// انا لو بعمل load اظهر ال bar بتاعي
@BindingAdapter("manageShimmerVisibilty")
fun manageShimmer(group: ShimmerFrameLayout, isShown: Boolean) {
    when (isShown) {
        true -> {
            group.startShimmerAnimation()
        }

        false -> {
            group.stopShimmerAnimation()
        }
    }
}


@BindingAdapter("clearFocus")
fun clearFocusOnEditText(view: EditText, isHide: Boolean) {

    when (isHide) {

        true -> {
            view.clearFocus()
        }

        false -> {
            //view.requestFocus()
        }

    }

}