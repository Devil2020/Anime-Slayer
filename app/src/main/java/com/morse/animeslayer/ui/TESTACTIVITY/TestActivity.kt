package com.morse.animeslayer.ui.TESTACTIVITY

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.expertapps.base.libraries.filter.base.BaseAdapter
import com.morse.animeslayer.R
import com.morse.common.adapter.isLinearLayout
import com.morse.common.adapter.onItemClick
import com.morse.common.adapter.submitData
import com.morse.common.adapter.withRecyclerView
import kotlinx.android.synthetic.main.activity_test2.*

class TestActivity : AppCompatActivity() {

/*    var testViewModel = TestViewModel()
    val flowOfIntents = MutableStateFlow(Intentions.getNumbers)*/
    lateinit var adapter: BaseAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }

    override fun onResume() {
        super.onResume()
        adapter = NumbersAdapter ()
            .onItemClick {
                Toast.makeText(this , "You Clicked on Item #${it} 🍕 " , Toast.LENGTH_SHORT).show()
            }.withRecyclerView(numbersRecyclerview)
            .isLinearLayout()
            .submitData(
                ArrayList( (0 .. 100).toList())
            )

  /*      retry_retry.setOnClickListener {
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
        }*/
    }
/*

    private fun command(command: Intentions) = testViewModel.executeIntention(command)*/

}