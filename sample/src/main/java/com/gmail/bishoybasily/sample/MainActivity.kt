package com.gmail.bishoybasily.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.listener.EndlessRecyclerViewScrollListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var count = 0

    @Volatile
    var fetching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapterThings = AdapterThings()

        recycler.adapter = adapterThings
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val loader = Thing.Loader("")

        adapterThings.append(fewThings())

        val visibleThreshold = 4
        recycler.addOnScrollListener(object : EndlessRecyclerViewScrollListener(visibleThreshold = visibleThreshold) {

            override fun onLoadMore() {

                thread {
                    if (!fetching) {
                        fetching = true
                        runOnUiThread {
                            adapterThings.appendOnce(loader)
                        }
                        Thread.sleep(1000)
                        runOnUiThread {
                            adapterThings.removeOnce(loader)
                            adapterThings.append(fewThings())
                            recycler.scrollToPosition(adapterThings.itemCount - (visibleThreshold * 2))
                        }
                        fetching = false
                    }
                }


            }

        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun fewThings(): List<Thing> {
        return (0..10).map { Thing("Name ${++count}") }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
