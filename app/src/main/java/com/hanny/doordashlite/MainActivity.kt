package com.hanny.doordashlite

import android.content.pm.PackageManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.hanny.doordashlite.helpers.LOCATION_REQUEST_CODE
import com.hanny.doordashlite.models.Restaurant
import com.hanny.doordashlite.view.LocalResListAdapter
import androidx.recyclerview.widget.DividerItemDecoration



class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: View
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        progressBar = findViewById(R.id.progress_bar)
        showLoading()
        DoorDashLiteManager.getLocation(this, fusedLocationClient)
    }


    fun presentLocalRestaurants(restaurants: ArrayList<Restaurant>) {
        dismissLoading()
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = LocalResListAdapter(restaurants)
        val recyclerView = findViewById<RecyclerView>(R.id.restaurants_list)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        );

        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    DoorDashLiteManager.getLocation(this, fusedLocationClient)
                } else {
                    // permission denied, boo! Disable the
                    DoorDashLiteManager.showError(this, "Permission denied", null);
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun dismissLoading() {
        progressBar.visibility = View.GONE
    }


}
