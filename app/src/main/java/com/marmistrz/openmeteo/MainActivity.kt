package com.marmistrz.openmeteo

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    val LOGTAG = "OpenMeteo"
    // val imageView = lazy { findViewById<ImageView>(R.id.imageView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun loadForecast(view: View) {
        val rowField = findViewById<EditText>(R.id.rowField)
        val colField = findViewById<EditText>(R.id.colField)
        val row = rowField.getText().toString()
        val col = colField.getText().toString()
        loadForecastByCoordinates(row, col)
    }

    fun loadForecastByCoordinates(row: String, col: String) {
        Log.i(LOGTAG, "Downloading the forecast for [$row, $col]")
        val url = "https://www.meteo.pl/um/metco/mgram_pict.php?ntype=0u&row=$row&col=$col&lang=pl"
        Log.d(LOGTAG, "Using the URL: $url")
        // FIXME call only once
        val imageView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load(url).into(imageView)
    }
}
