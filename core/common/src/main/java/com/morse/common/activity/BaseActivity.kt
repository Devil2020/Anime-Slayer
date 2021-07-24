package com.expertapps.base.activity

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.expertapps.base.ActionBarConfig
import com.morse.common.R
import com.morse.common.constants.Constants
import com.morse.common.databinding.ActivityBaseBinding
import io.paperdb.Paper
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseActivity : AppCompatActivity() {
    private val baseActivityViewModel by viewModels<BaseActivityViewModel>()
    var activityBaseBinding: ActivityBaseBinding? = null
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(updateBaseContextLocale(newBase ?: applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBaseBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_base
        )
        activityBaseBinding?.lifecycleOwner = this
        setSupportActionBar(activityBaseBinding?.toolbar)

        baseActivityViewModel.actionBarConfig.observe(this, {
            supportActionBar?.setDisplayShowTitleEnabled(false)//to hide default toolbar title
            supportActionBar?.setDisplayShowHomeEnabled(it.displayShowHomeEnabled)
            supportActionBar?.setDisplayHomeAsUpEnabled(it.displayHomeAsUpEnabled)
            supportActionBar?.setHomeAsUpIndicator(it.upIndicator)
            Glide.with(this).asDrawable().load(it?.logoUrlFromNetwork)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        supportActionBar?.setLogo(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })

        })

        activityBaseBinding?.baseActivityViewModel = baseActivityViewModel
    }

    protected fun setLayoutContainerContent(@LayoutRes layoutRes: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            layoutInflater,
            layoutRes,
            activityBaseBinding?.layoutContainer,
            true
        )
    }

    protected fun setActionBarConfig(newActionBarConfig: ActionBarConfig) {
        baseActivityViewModel.actionBarConfig.value = newActionBarConfig
    }

    protected fun getActionBarConfig(): ActionBarConfig {
        return baseActivityViewModel.actionBarConfig.value!!
    }

    fun getToolbarLogoIcon(): View? {
        //check if contentDescription previously was set
        val toolbar = activityBaseBinding?.toolbar ?: Toolbar(this)
        val hadContentDescription =
            TextUtils.isEmpty(toolbar.logoDescription)
        val contentDescription =
            if (!hadContentDescription) toolbar.logoDescription else "logoContentDescription"
        toolbar.logoDescription = contentDescription
        val potentialViews: ArrayList<View> = ArrayList()
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(
            potentialViews,
            contentDescription,
            View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION
        )
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        var logoIcon: View? = null
        if (potentialViews.size > 0) {
            logoIcon = potentialViews[0]
        }
        //Clear content description if not previously present
        if (hadContentDescription) toolbar.logoDescription = null
        return logoIcon ?: toolbar
    }

    private fun updateBaseContextLocale(context: Context): Context {
        val language = Paper.book().read<String>(Constants.CURRENT_LANGUAGE,Constants.AR)
        val locale = Locale(language)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale)
        }

        return updateResourcesLocaleLegacy(context, locale)
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    private fun updateResourcesLocale(context: Context, locale: Locale): Context {
        val configuration = Configuration(context.getResources().getConfiguration())
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    @SuppressWarnings("deprecation")
    private fun updateResourcesLocaleLegacy(context: Context, locale: Locale): Context {
        val resources = context.getResources()
        val configuration = resources.getConfiguration()
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        return context
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        var view = currentFocus
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() ==
                    MotionEvent.ACTION_MOVE) && view is EditText &&
            !view.javaClass.getName().startsWith("android.webkit.")
        ) {
            var scrcoords = intArrayOf(0, 0)
            view.getLocationOnScreen(scrcoords)
            val x = ev.getRawX() + view.getLeft() - scrcoords[0]
            val y = ev.getRawY() + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) {
                hideKeyboard()
                view.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}