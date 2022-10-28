package com.bhaveshp750.junoassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.bhaveshp750.junoassignment.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var progressView: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        progressView = findViewById(R.id.progress_view)

        viewModel.homeDto.observe(this) { homeDto ->
            homeDto?.let {
                Log.d(TAG, "onCreate: homeDto $homeDto")
            }
        }
        viewModel.isLoading.observe(this) { isLoading ->
            isLoading?.let {
                Log.d(TAG, "onCreate: isLoading $isLoading")
            }
        }
        viewModel.fetchError.observe(this) { isError ->
            isError?.let {
                Log.d(TAG, "onCreate: fetchError $isError")
                progressView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}