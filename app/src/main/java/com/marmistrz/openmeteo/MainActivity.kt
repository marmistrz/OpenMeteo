package com.marmistrz.openmeteo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val LOGTAG = "OpenMeteo"
    private val imageView by lazy { findViewById<ImageView>(R.id.imageView) }
    private val rowField by lazy { findViewById<EditText>(R.id.rowField) }
    private val colField by lazy { findViewById<EditText>(R.id.colField) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawerLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    @Suppress("UNUSED_PARAMETER")
    fun loadForecast(view: View) {
        val row = rowField.getText().toString()
        val col = colField.getText().toString()
        loadForecastByCoordinates(row, col)
    }

    private fun loadForecastByCoordinates(row: String, col: String) {
        Log.i(LOGTAG, "Downloading the forecast for [$row, $col]")
        val url = "https://www.meteo.pl/um/metco/mgram_pict.php?ntype=0u&row=$row&col=$col&lang=pl"
        Log.d(LOGTAG, "Using the URL: $url")
        Picasso.get().load(url).into(imageView)
    }
}
