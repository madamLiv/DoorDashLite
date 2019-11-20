package com.hanny.doordashlite.remote

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

typealias Params = HashMap<String, String>

class APIService {

    fun performApiRequest(context:Context, params: Params?, callback: (String?) -> Unit) {
        val queue = Volley.newRequestQueue(context)
        var url = getBaseUrl() + "/v2/restaurant"

        params?.let {
            url += "?"
            for (key in params.keys) {
                url += key + "=" + params[key] + "&"
            }
        }

        val sr = object : StringRequest(Method.GET, url, Response.Listener { response ->
            callback(response)
        }, Response.ErrorListener { error ->
            if (error?.message != null) {
                Log.e("APIService", error.message!!)
                callback(null) // show error
            }
        }) {

        }
        queue.add(sr)
    }

    private fun getBaseUrl(): String {
        return "https://api.doordash.com"
    }
}