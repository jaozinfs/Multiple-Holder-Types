package com.example.multipleholderrecyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.multipleholderrecyclerview.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.example.multipleholderrecyclerview.databinding.ActivityMainBinding
import com.example.multipleholderrecyclerview.utils.TextEmptyWatcher
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import androidx.recyclerview.widget.RecyclerView




class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityGameBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        rV.layoutManager?.smoothScrollToPosition(rV, RecyclerView.State(), 0)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        activityGameBinding.viewModel = viewModel

        GlobalScope.launch {
            delay(3000)
            withContext(Dispatchers.Main) { viewModel.setVisibility(false) }

        }
        editText.addTextChangedListener(TextEmptyWatcher {
            viewModel._sendPostVisibilty.value = it
        })
    }
    fun addMessage(view:View){
        viewModel.addMessage(editText.text.toString()){
            rV.adapter?.let {
                rV.scrollToPosition(it.itemCount - 1)
                clearPostEd()
            }
        }
    }

    private fun clearPostEd(){
        editText.setText(getString(R.string.post_hint))
    }
}
