package com.marmistrz.openmeteo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marmistrz.openmeteo.database.Location
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val LOGTAG = "OpenMeteo"
    private val imageView by lazy { findViewById<ImageView>(R.id.imageView) }
    private val rowField by lazy { findViewById<EditText>(R.id.rowField) }
    private val colField by lazy { findViewById<EditText>(R.id.colField) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawerLayout) }
    private val locationList by lazy { findViewById<RecyclerView>(R.id.location_list) }

    private val locations = listOf(Location("Warsaw", 406, 250), Location("Lublin", 432, 277))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect the action bar and the navigation drawer
        setSupportActionBar(toolbar)
        val toggle =
                ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.drawerOpen,
                        R.string.drawerClose
                )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Populate the navigation drawer
        populateDrawer()
    }

    @Suppress("UNUSED_PARAMETER")
    fun loadForecastOnClick(view: View) {
        try {
            val row = rowField.getText().toString().toInt()
            val col = colField.getText().toString().toInt()
            loadForecastByCoordinates(row, col)
        } catch (e: NumberFormatException) {
            Log.e(LOGTAG, "Invalid coordinates")
        }
    }

    private fun loadForecast(location: Location) {
        loadForecastByCoordinates(location.row, location.column)
    }

    private fun loadForecastByCoordinates(row: Int, col: Int) {
        Log.i(LOGTAG, "Downloading the forecast for [$row, $col]")
        val url = "https://www.meteo.pl/um/metco/mgram_pict.php?ntype=0u&row=$row&col=$col&lang=pl"
        Log.d(LOGTAG, "Using the URL: $url")
        Picasso.get().load(url).into(imageView)
    }

    private fun populateDrawer() {
        locationList.adapter = LocationsAdapter()
        locationList.layoutManager = LinearLayoutManager(this)
    }

    inner class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val location = locations[position]
            val name = location.name
            holder.textField.text = name
            holder.textField.setOnClickListener {
                Log.i(LOGTAG, "Loading forecast for $name.")
                loadForecast(location)
                drawerLayout.closeDrawers()
            }
        }

        override fun getItemCount(): Int = locations.size

        inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
            var textField = view.findViewById<TextView>(android.R.id.text1)
        }
    }
}
