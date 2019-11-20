package com.hanny.doordashlite

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Test

import com.hanny.doordashlite.models.Restaurant
import org.junit.Assert.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun parseResponseTest() {
        //given
        val json = "[{\n" +
                "  \"is_time_surging\": false,\n" +
                "  \"max_order_size\": null,\n" +
                "  \"delivery_fee\": 0,\n" +
                "  \"max_composite_score\": 10,\n" +
                "  \"id\": 215685,\n" +
                "  \"merchant_promotions\": [],\n" +
                "  \"average_rating\": 4.2,\n" +
                "  \"menus\": [\n" +
                "    {\n" +
                "      \"popular_items\": [],\n" +
                "      \"is_catering\": false,\n" +
                "      \"subtitle\": \"All Day\",\n" +
                "      \"id\": 291198,\n" +
                "      \"name\": \"99 Cents Famous Pizza (All Day) (New York)\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"composite_score\": 8,\n" +
                "  \"status_type\": \"open\",\n" +
                "  \"is_only_catering\": false,\n" +
                "  \"status\": \"33 mins\",\n" +
                "  \"number_of_ratings\": 73,\n" +
                "  \"asap_time\": 33,\n" +
                "  \"description\": \"Pizza, Pickup\",\n" +
                "  \"business\": {\n" +
                "    \"id\": 116376,\n" +
                "    \"name\": \"99 Cents Famous Pizza\"\n" +
                "  },\n" +
                "  \"tags\": [\n" +
                "    \"Pizza\",\n" +
                "    \"Pickup\"\n" +
                "  ],\n" +
                "  \"yelp_review_count\": 0,\n" +
                "  \"business_id\": 116376,\n" +
                "  \"extra_sos_delivery_fee\": 0,\n" +
                "  \"yelp_rating\": 0,\n" +
                "  \"cover_img_url\": \"https://cdn.doordash.com/media/restaurant/cover/99CentsFamousPizza12810016.png\",\n" +
                "  \"header_img_url\": \"\",\n" +
                "  \"address\": {\n" +
                "    \"city\": \"New York\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"street\": \"1 East 28th Street\",\n" +
                "    \"lat\": 40.7447029,\n" +
                "    \"lng\": -73.9870023,\n" +
                "    \"printable_address\": \"1 E 28th St, New York, NY 10016, USA\"\n" +
                "  },\n" +
                "  \"price_range\": 2,\n" +
                "  \"slug\": \"99-cents-famous-pizza-new-york\",\n" +
                "  \"name\": \"99 Cents Famous Pizza (28th St)\",\n" +
                "  \"is_newly_added\": false,\n" +
                "  \"url\": \"/store/99-cents-famous-pizza-new-york-215685/\",\n" +
                "  \"service_rate\": 11,\n" +
                "  \"promotion\": null,\n" +
                "  \"featured_category_description\": null\n" +
                "}]"


        //when
        var restaurants: Array<Restaurant>
        restaurants = Gson().fromJson(json, Array<Restaurant>::class.java)

        //then
        val restaurant = restaurants[0]
        assertEquals(restaurant.name, "99 Cents Famous Pizza (28th St)")
        assertEquals(restaurant.id, 215685)
        assertEquals(
            restaurant.cover_img_url,
            "https://cdn.doordash.com/media/restaurant/cover/99CentsFamousPizza12810016.png"
        )


    }

}
