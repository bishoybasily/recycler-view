package com.fidelyo.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.fidelyo.recyclerview.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val ada = AdapterThings()

        recycler.adapter = ada
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ada.clickListener = object : RecyclerViewAdapter.OnItemClickListener<Thing> {
            override fun onClicked(i: Thing, view: View) {
                Log.w("@@", "Clicked ${i.name}")
                i.setSelected(!i.selected)
            }

        }
        ada.longClickListener = object : RecyclerViewAdapter.OnItemLongClickListener<Thing> {
            override fun onLongClicked(i: Thing, view: View): Boolean {
                Log.w("@@", "Long clicked ${i.name}")
                return true
            }
        }

        val things = ArrayList<Thing>()
        for (i in 0..10) {
            things.add(Thing().apply { name = "Name $i" })
        }

        ada.showAll(things)

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
