package me.raghu.raghunandan_kavi

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : AppCompatActivity() {

    private lateinit var connectivityChecker: ConnectivityChecker
    private lateinit var model: MainActivityViewModel

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val connectivityManager = this@MainActivity.getSystemService<ConnectivityManager>()
        if (connectivityManager != null) {
            connectivityChecker = ConnectivityChecker(connectivityManager)
        }

        supportActionBar?.title = "Data Display"
        model = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        fetch_data.setOnClickListener {
            checkConnection()
        }
        no_network.setOnClickListener {
            checkConnection()
        }
    }

    @ExperimentalCoroutinesApi
    private fun checkConnection() {
        connectivityChecker.apply {
            lifecycle.addObserver(this)
            connectedStatus.observe(this@MainActivity, Observer<Boolean> {
                if (it) {
                    loadData()
                } else {
                    showError()
                }
            })
        }
    }

    @ExperimentalCoroutinesApi
    private fun loadData() {
        root.visibility = View.VISIBLE
        no_network.visibility = View.GONE
        model.getTenthChar().observe(this, Observer<String> { value ->
            value?.let { textView1.text = it }
        })
        model.getEveryTenthChar().observe(this, Observer<String> { value ->
            value?.let { textView2.text = it }
        })
        model.getWordsCount().observe(this, Observer<String> { value ->
            value?.let { textView3.text = it }
        })
    }

    private fun showError() {
        root.visibility = View.GONE
        no_network.visibility = View.VISIBLE
    }
}
