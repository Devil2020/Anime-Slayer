package com.morse.animeslayer.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimeRecommendationResponse(
    @SerializedName("recommendations")
    val recommendations: List<Recommendation>,
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int, // 1666
    @SerializedName("request_cached")
    val requestCached: Boolean, // true
    @SerializedName("request_hash")
    val requestHash: String // request:anime:310644adeeae949148432c8c932b3f53c40adb75
) {
    @Keep
    data class Recommendation(
        @SerializedName("image_url")
        val imageUrl: String, // https://cdn.myanimelist.net/images/anime/5/82890.jpg?s=b8b3b76d7d72c1730859e6e0f7a88dd7
        @SerializedName("mal_id")
        val malId: Int, // 32615
        @SerializedName("recommendation_count")
        val recommendationCount: Int, // 7
        @SerializedName("recommendation_url")
        val recommendationUrl: String, // https://myanimelist.net/recommendations/anime/32615-40028
        @SerializedName("title")
        val title: String, // Youjo Senki
        @SerializedName("url")
        val url: String // https://myanimelist.net/anime/32615/Youjo_Senki
    )
}