package me.raghu.raghunandan_kavi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Data Display"
        val model = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        fetch_data.setOnClickListener {
            model.launchDataLoad()
        }
        model.tenthChar.observe(this, Observer<String> { value ->
            value?.let { textView1.text = it }
        })
        model.everyTenthChar.observe(this, Observer<String> { value ->
            value?.let { textView2.text = it }
        })
        model.wordsCount.observe(this, Observer<String> { value ->
            value?.let { textView3.text = it }
        })
    }
}
