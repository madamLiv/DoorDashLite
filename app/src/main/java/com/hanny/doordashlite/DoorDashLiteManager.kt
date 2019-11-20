package com.hanny.doordashlite

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import com.google.gson.Gson
import com.hanny.doordashlite.helpers.LOCATION_REQUEST_CODE
import com.hanny.doordashlite.models.Restaurant
import com.hanny.doordashlite.remote.APIService
import com.hanny.doordashlite.remote.Params
import org.json.JSONArray
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder


object DoorDashLiteManager {

    private val apiService = APIService()

    fun getRestaurantsList(context: Context, lat: String, lng: String, callback: (ArrayList<Restaurant>) -> Unit) {

        var params = Params()
        params["lat"] = lat
        params["lng"] = lng
        params["offset"] = "0"
        params["limit"] = "50"

        apiService.performApiRequest(context, params) { response: String? ->
            if (response != null) {
                parseResponse(response) {
                    callback(it)
                }
            } else {
                showError(context, "Error ", "Bad connection, Try again later")
            }
        }
    }

    fun getLocation(activity: Activity, fusedLocationClient: FusedLocationProviderClient) {
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
        } else {
            // permissions granted
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        getRestaurantsList(
                            activity,
                            location.latitude.toString(),
                            location.longitude.toString()
                        ) { response: ArrayList<Restaurant> ->
                            if (activity is MainActivity) {
                                activity.presentLocalRestaurants(response)
                            }
                        }
                    } else {
                        showError(activity, "Location Error", "No location found")
                    }
                }
        }
    }

    private fun parseResponse(response: String, callback: (ArrayList<Restaurant>) -> Unit) {
        var localRestaurants = ArrayList<Restaurant>()
        val data = try {
            JSONArray(response)
        } catch (e: Exception) {
            return
        }
        for (i in 0 until data.length()) {
            val restaurantJson = data.getJSONObject(i)
            val localRes = Gson().fromJson(restaurantJson.toString(), Restaurant::class.java)
            localRestaurants.add(localRes)
        }

        callback(localRestaurants)
    }

    fun showError(context: Context, title: String, message: String?) {
        var error = AlertDialog.Builder(context)
            .setTitle(title)
            .setIcon(android.R.drawable.stat_notify_error)
        message?.let {
            error.setMessage(message)
        }
        error.show()
    }
}