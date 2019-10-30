package com.nooblabs.sampleapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nooblabs.quickrvadapter.adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.flowOf

class MainActivity : AppCompatActivity() {

    private val rvAdapter by lazy {
        adapter(R.layout.item_layout, listOf("One", "Two")) {
            bindings {
                bind<TextView>(R.id.count_text) { datum, countTextView ->
                    countTextView.text = datum
                }
            }
        }
    }

    private val rvAdapter2 by lazy {
        adapter(R.layout.item_layout, flowOf("One", "Two")) {
            bindings {
                bind<TextView>(R.id.count_text) { datum, countTextView ->
                    countTextView.text = datum
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = rvAdapter2
    }
}
