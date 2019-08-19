package com.marmistrz.openmeteo

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.lang.Exception
import java.net.URL
import android.os.StrictMode
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    val LOGTAG = "OpenMeteo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun loadForecast(view: View) {

        Log.e(LOGTAG, "xD")
        val url = "https://www.meteo.pl/um/metco/mgram_pict.php?ntype=0u&row=406&col=250&lang=pl"
        val imageView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load(url).into(imageView)

        Log.e(LOGTAG, "done")
    }
}
