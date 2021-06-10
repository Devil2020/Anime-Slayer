package com.expertapps.base.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.expertapps.base.extensions.hideVisibilty
import com.expertapps.base.extensions.showVisibilty
import com.expertapps.base.extensions.startShimmerAnimation
import com.expertapps.base.extensions.stopShimmerAnimation
import com.facebook.shimmer.ShimmerFrameLayout
import com.morse.common.R
import com.morse.common.network.common.ExceptionType
import com.morse.common.network.common.ResponseStateErrorData


class ShimmerResponseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private var typedArray: TypedArray

    private var layoutInflator: LayoutInflater
    private var root: View

    // loading views
    private var loadingRoot: ShimmerFrameLayout
    private var loadingContainer: LinearLayout

    // error views
    private var errorRoot: ScrollView
    private var errorImage: ImageView
    private var errorMessageTitle: TextView
    private var errorMessageBody: TextView
    private var errorRetryBtn: Button

    // exceptionType and retryError action

    private var retryAction : (() -> Unit)? =null
    var exceptionType : ExceptionType ?= ExceptionType.UnknownHostException

    init {
        typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ShimmerResponseView, 0, 0)
        layoutInflator = LayoutInflater.from(context)
        root =
            layoutInflator.inflate(R.layout.response_state_handler_layout, this, true)
        // loading views
        loadingRoot = root.findViewById(R.id.loadingShimmerRoot)
        loadingContainer = root.findViewById(R.id.loadingContainer)
        // error views
        errorRoot = root.findViewById(R.id.errorLayout)

        errorImage = root.findViewById(R.id.errorImageView)
        errorMessageTitle = root.findViewById(R.id.errorMessageTitleTextView)
        errorMessageBody = root.findViewById(R.id.errorMessageBodyTextView)
        errorRetryBtn = root.findViewById(R.id.retry_btn)

        hideRoot()
    }

    fun isEmpty() {
        showRoot()
        loadingRoot.apply {
            stopShimmerAnimation()
        }
        errorRoot.apply {
            showVisibilty()
            errorImage.setImageResource(R.drawable.ic_empty_icon)
            errorMessageTitle.text =
                typedArray.getString(R.styleable.ShimmerResponseView_emptyMessageTitle)
            errorMessageBody.text =
                typedArray.getString(R.styleable.ShimmerResponseView_emptyMessageBody)
            errorRetryBtn.apply {
                hideVisibilty()
            }
        }
    }

    fun isError() {
        val error = getResponseStateErrorData(exceptionType ?: ExceptionType.UnknownHostException)
        Log.e("ExceptionType", "The Index of ExceptionType is ${error.id}")
        showRoot()
        loadingRoot.apply {
            stopShimmerAnimation()
        }
        errorRoot.apply {
            showVisibilty()
            errorImage.setImageResource(error.errorDrawableRes)
            errorMessageTitle.text = error.errorTitle
            errorMessageBody.text = error.errorMessage
            errorRetryBtn.apply {
                showVisibilty()
                setOnClickListener { retryAction?.invoke() }
            }
        }
    }

    public fun setErrorOnRetryAtion (callback : () -> Unit){
        retryAction = callback
    }

    @BindingAdapter("ErrorExceptionType")
    fun setErrorLiveData(
        view: ShimmerResponseView,
        exceptionType: ExceptionType ?= null
    ) {
        view.exceptionType = exceptionType
    }

    fun isLoading() {
        showRoot()
        errorRoot.apply {
            hideVisibilty()
        }
        loadingRoot.apply {
            if (loadingContainer.childCount == 0) {
                for (index in 0 until typedArray.getInt(
                    R.styleable.ShimmerResponseView_loadingCount,
                    0
                )) {
                    val item = layoutInflator.inflate(
                        typedArray.getResourceId(
                            R.styleable.ShimmerResponseView_loadingItem,
                            0
                        ), null, false
                    )
                    loadingContainer.addView(item)
                }
            }
            startShimmerAnimation()
        }
    }

    fun hideRoot() {
        root.hideVisibilty()
    }

    private fun showRoot() {
        root.showVisibilty()
    }

    private fun getResponseStateErrorData(exceptionType: ExceptionType): ResponseStateErrorData {
        return when (exceptionType) {
            ExceptionType.HttpException_404 -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.not_found_error_body_label)
            )
            ExceptionType.HttpException_422 -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.content_type_error_body_label)
            )
            ExceptionType.HttpException_403 -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.forbidden_error_body_label)
            )
            ExceptionType.HttpException_504 -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.service_unavailable_error_body_label)
            )
            ExceptionType.HttpException_401 -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.unauthorized_error_body_label)
            )
            ExceptionType.HttpException_400 -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.bad_request_error_body_label)
            )
            ExceptionType.HttpException_Generic -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.generic_error_body_label)
            )
            ExceptionType.UserCancellationException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.user_cancel_error_body_label)
            )
            ExceptionType.EOFException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.internal_server_error_body_label)
            )
            ExceptionType.GenericException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.generic_error_body_label)
            )
            ExceptionType.ProtocolException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.protocol_error_body_label)
            )
            ExceptionType.SSLException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.ssl_error_body_label)
            )
            ExceptionType.SSLHandshakeException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.ssl_error_body_label)
            )
            ExceptionType.SocketException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.timeout_error_body_label)
            )
            ExceptionType.SocketTimeoutException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internal_serval_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.timeout_error_body_label)
            )
            ExceptionType.UnknownHostException -> return ResponseStateErrorData(
                exceptionType.ordinal,
                R.drawable.ic_internet_not_available_error,
                context.getString(R.string.error_header_label),
                context.getString(R.string.no_internet_error_body_label)
            )
        }
    }

}

