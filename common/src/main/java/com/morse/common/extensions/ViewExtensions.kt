package com.expertapps.base.extensions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.morse.common.R
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlin.math.abs


fun EditText.onValueChanged(onChange: (String) -> Unit) {
    var xxx: Job? = null
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            xxx?.cancel()
            xxx = launchOnUI {
                delay(250)
                onChange.invoke(s.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            xxx?.cancel()
        }
    })
}

fun EditText.intOrZero(): Int {
    val s = text.toString()
    return if (s.isEmpty()) 0 else s.toInt()
}

fun TabLayout.setOnChanged(function: (pos: Int) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {
        }

        override fun onTabSelected(p0: TabLayout.Tab) {
            function.invoke(p0.position)
        }
    })
}

fun EditText.sub(delay: Long? = 300, runnable: Runnable) {
    addTextChangedListener(MyWatcher(runnable,delay) )
}

class MyWatcher(private val runnable: Runnable, private val delay: Long? = 300) : TextWatcher {
    private val handlerThread = Handler(Looper.getMainLooper())
    override fun afterTextChanged(s: Editable?) {
        handlerThread.removeCallbacks(runnable)
        handlerThread.postDelayed(runnable, delay ?: 10)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}

fun String?.isNotShort(min: Int) = this?.length ?: 0 > min

fun android.view.View.showKeyBoard(context: Context) = (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager)
        .showSoftInput(this, 0)

fun android.view.View.hideKeyBoard(context: Context) = (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager)
        .hideSoftInputFromWindow(this.windowToken, 0)

fun ImageView.loadCirculeImage(
    imageUrl: String? = null,
    userName: String? = "") {
    this.showVisibilty()
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 1f
    circularProgressDrawable.centerRadius = 35f
    circularProgressDrawable.start()
    if (TextUtils.isEmpty(imageUrl)) {
        val imageApi = resources.getString(R.string.user_Image_label, userName)
        Glide.with(context).load(imageApi).placeholder(circularProgressDrawable)
            .error(R.drawable.ic_error_user_image_icon).into(this)

    } else {
        Glide.with(context).load(imageUrl).placeholder(circularProgressDrawable).circleCrop()
            .error(R.drawable.ic_error_user_image_icon).into(this)
    }
}

fun FloatingActionButton.loadCirculeSrc(
    imageUrl: String? = null,
    userName: String? = "") {
    this.showVisibilty()
    if (TextUtils.isEmpty(imageUrl)) {
        setSrc(resources.getString(R.string.user_Image_label, userName))
    } else {
        setSrc(imageUrl ?: "")
    }

}

private fun FloatingActionButton.setSrc(imageUrl: String) {
    Glide.with(this).asDrawable().circleCrop()
        .load(imageUrl)
        .into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                setImageDrawable(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                setImageDrawable(placeholder)
            }
        })
}

fun TextView.setUnReadMessagesCount(count: Int?) {
    if (count != null) {
        if (count > 0) {
            visibility = View.VISIBLE
            text = "$count"
        } else {
            visibility = View.GONE
        }
    } else {
        visibility = View.GONE
    }
}

fun TabLayout.setupTabs(
    tabsName: ArrayList<String>,
    tabListener: TabLayout.OnTabSelectedListener?) {
    for (tab in tabsName) {
        addTab(createTab(tab))
    }
    addOnTabSelectedListener(tabListener)
}

private fun TabLayout.createTab(tabName: String): TabLayout.Tab {
    return newTab().setText(tabName)
}

fun ShimmerFrameLayout.startShimmerAnimation() {
    if (visibility != View.VISIBLE) {
        startShimmer()
        visibility = View.VISIBLE
    }
}

fun ShimmerFrameLayout.stopShimmerAnimation() {
    if (visibility != View.GONE) {
        stopShimmer()
        visibility = View.GONE
    }
}

fun ShimmerFrameLayout.manageShimmer(isLoading: Boolean) {
    if (isLoading) {
        startShimmerAnimation()
    } else {
        stopShimmerAnimation()
    }
}

fun View.manageVisibilty(isVisible: Boolean) {
    if (isVisible) {
        showVisibilty()
    } else {
        hideVisibilty()
    }
}

fun View.hideVisibilty() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.showVisibilty() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun TextView.setTextNullable(text: String?, layout: ConstraintLayout) {
    if (TextUtils.isEmpty(text)) {
        layout.hideVisibilty()
    } else {
        layout.showVisibilty()
        setText(text)
    }
}

// I have 3 Cases
//          1- Users Less than 3
//          2- Users equal 3
//          3- Users bigger than 3


fun TextView.manageExpandAndCollapse() {
    if (visibility == View.GONE) {
        animateAlphaOfTextViewTo1F()
    } else {
        animateAlphaOfTextViewTo0F()
    }
}

fun TextView.animateVisibiltyOfTextViewToON() {
    animate()
        .translationY(-10f)
        .alpha(1f)
        .setDuration(200)
        .setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                this@animateVisibiltyOfTextViewToON.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
        .start()


}

fun TextView.animateVisibiltyOfTextViewToOFF() {
    animate()
        .alpha(0f)
        .setDuration(200)
        .setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                this@animateVisibiltyOfTextViewToOFF.visibility = View.GONE
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
        .start()


}

fun TextView.animateAlphaOfTextViewTo1F() {
    val animation = ObjectAnimator.ofInt(
        this,
        "visibility",
        0x00000000
    )
    animation.duration = 200
    animation.start()
}

fun TextView.animateAlphaOfTextViewTo0F() {
    val animation = ObjectAnimator.ofInt(
        this,
        "visibility",
        0x00000008
    )
    animation.duration = 200
    animation.start()
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.isGone() = this.visibility == View.GONE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    if (isVisible()) {
        this.visibility = View.GONE
    }
}

fun View.visibleIf(boolean: Boolean) = if (boolean) visible() else gone()

fun View.visibleIf(boolean: Boolean?) = if (boolean == true) visible() else gone()

fun View.enableIf(boolean: Boolean) = if (boolean) enable() else disable()

fun View.visibleOrInvIf(boolean: Boolean) = if (boolean) visible() else invisible()

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

fun android.widget.TextView.visibleIf(value: String?) {
    if (!value.isNullOrEmpty()) {
        this.visible()
        this.text = value
    } else this.gone()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ViewPager2.addHorizontalMarginItemDecoration (@DimenRes horizontalMarginInDp: Int){
    val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()
    val itemDecorator = object : RecyclerView.ItemDecoration (){
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }
    }
    addItemDecoration(itemDecorator)
}

fun ViewPager2.setCarouselEffects(){
    offscreenPageLimit = 1

    val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
    val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
    val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
        page.translationX = -pageTranslationX * position
        page.scaleY = 1 - (0.25f * abs(position))
        page.alpha = 0.5f + (1 - abs(position))
    }
    setPageTransformer(pageTransformer)
}