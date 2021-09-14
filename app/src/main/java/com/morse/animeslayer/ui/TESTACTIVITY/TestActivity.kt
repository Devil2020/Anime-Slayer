package com.morse.animeslayer.ui.TESTACTIVITY

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.morse.animeslayer.R
import kotlinx.android.synthetic.main.activity_test2.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity() {

    var testViewModel = TestViewModel()
    val flowOfIntents = MutableStateFlow(Intentions.getNumbers)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }

    override fun onResume() {
        super.onResume()


        retry_retry.setOnClickListener {
            lifecycleScope.launch {
                command(Intentions.retry)
                    .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                    .onStart { textAaa.text = "Now we loading from network" }
                    .catch { textAaa.text = "Error happened Here ." }.collect {
                    textAaa.text = "The New Value is $it"
                }
            }
        }

        swip_swipe.setOnClickListener {
            lifecycleScope.launch {
                command(Intentions.retry)
                    .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                    .onStart { textAaa.text = "Now we loading from network" }
                    .catch { textAaa.text = "Error happened Here ." }.collect {
                    textAaa.text = "The New Value is $it"
                }
            }
        }

        testViewModel.executeIntentionWithTransfer(flowOfIntents)

        lifecycleScope.launch {
            testViewModel.executeIntention(Intentions.getNumbers)
                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .onStart { textAaa.text = "Now we loading from network" }
                .catch { textAaa.text = "Error happened Here ." }.collect {
                textAaa.text = "The New Value is $it"
            }
        }
    }


    private fun command(command: Intentions) = testViewModel.executeIntention(command)

}