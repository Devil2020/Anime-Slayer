package com.morse.animeslayer.ui.test

import com.morse.animeslayer.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.expertapps.base.extensions.showLog
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.flow.collect

class TestActivity : AppCompatActivity() {

    val viewmodel : TestActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        showLog("TestActivity : onCreate")

    }

    override fun onStart() {
        super.onStart()
        showLog("TestActivity : onStart")
    }

    override fun onResume() {
        super.onResume()
        showLog("TestActivity : onResume")
        lifecycleScope.launchWhenResumed {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                /*viewmodel.pushNews()
                viewmodel.getNews.collect {
                    startButton.text = "$it"
                }*/
            }
        }
    }

    override fun onPause() {
        super.onPause()
        showLog("TestActivity : onPause")
    }

    override fun onStop() {
        super.onStop()
        showLog("TestActivity : onStop")
    }

    override fun onRestart() {
        super.onRestart()
        showLog("TestActivity : onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("TestActivity : onDestroy")
    }

}