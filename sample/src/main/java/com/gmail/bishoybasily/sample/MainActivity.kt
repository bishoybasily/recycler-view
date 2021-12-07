package com.gmail.bishoybasily.sample

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapterThings = AdapterThings()

        recycler.adapter = adapterThings
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        adapterThings.onClick { i, _ ->
            Log.w("@@", "Clicked ${i.name}")
            i.setSelected(!i.selected)
        }

        adapterThings.onLongClick { i, _ ->
            Log.w("@@", "Long clicked ${i.name}")
            return@onLongClick true
        }

        val things = ArrayList<Thing>()
        for (i in 0..10) {
            things.add(Thing("Name $i"))
        }

        val loader = Thing.Loader("loooooooaaaader")

        adapterThings.append(Thing("new appended"))

        adapterThings.show(things)

        recycler.addOnScrollListener(object : EndlessRecyclerViewScrollListener() {

            override fun onLoadMore() {

                thread {
                    runOnUiThread { if (!adapterThings.items.contains(loader)) adapterThings.append(loader) }
                    Thread.sleep(5000)
                    runOnUiThread { if (adapterThings.items.contains(loader)) adapterThings.remove(loader) }
                }


            }

        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
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
