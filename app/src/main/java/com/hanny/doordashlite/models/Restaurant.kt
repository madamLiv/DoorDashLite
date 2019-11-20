package com.hanny.doordashlite.models

data class Restaurant(
    val id: Long,
    val name: String,
    val description: String,
    var cover_img_url: String,
    val status: String,
    val delivery_fee: String)