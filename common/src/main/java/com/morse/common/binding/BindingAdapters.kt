/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.expertapps.base.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.expertapps.base.extensions.*


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        loadCirculeImage(url)
    }
}

@BindingAdapter("imageRes")
fun ImageView.loadImage(res: Int) {
    setImageResource(res)
}

@BindingAdapter("imageCorner")
fun ImageView.loadImageCorner(imageUrl: String) {
    loadImageWithCornerRadius(imageUrl , "Error" , 40)
}

@BindingAdapter("viewIf")
fun viewIf(view: View, boolean: Boolean?) = view.visibleIf(boolean)



@BindingAdapter("toDate")
fun TextView.setDate(dataString: String?) {
    text = dataString?.convertDateWithTime()

}

