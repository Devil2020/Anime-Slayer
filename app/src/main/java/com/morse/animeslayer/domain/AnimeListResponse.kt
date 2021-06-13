package com.morse.animeslayer.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimeListResponse(
    @SerializedName("anime")
    val anime: List<Anime>?= null,
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int?= null, // 2103
    @SerializedName("request_cached")
    val requestCached: Boolean?= null, // true
    @SerializedName("request_hash")
    val requestHash: String?= null, // request:season:7bce9e37b2e6b6ca1ca3a2b9af1280ea8ee43aee
    @SerializedName("season_name")
    val seasonName: String?= null, // Winter
    @SerializedName("season_year")
    val seasonYear: Int?= null // 2021
) {
    @Keep
    data class Anime(
        @SerializedName("airing_start")
        val airingStart: String?= null, // 2020-12-06T15:10:00+00:00
        @SerializedName("continuing")
        val continuing: Boolean?= null, // false
        @SerializedName("episodes")
        val episodes: Int?= null, // 16
        @SerializedName("genres")
        val genres: List<Genre>?= null,
        @SerializedName("image_url")
        val imageUrl: String?= null, // https://cdn.myanimelist.net/images/anime/1000/110531.jpg
        @SerializedName("kids")
        val kids: Boolean?= null, // false
        @SerializedName("licensors")
        val licensors: List<String>?= null,
        @SerializedName("mal_id")
        val malId: Int?= null, // 40028
        @SerializedName("members")
        val members: Int?= null, // 1123762
        @SerializedName("producers")
        val producers: List<Producer>?= null,
        @SerializedName("r18")
        val r18: Boolean?= null, // false
        @SerializedName("score")
        var score: Double?= null
        , // 8.99
        @SerializedName("source")
        val source: String?= null, // Manga
        @SerializedName("synopsis")
        val synopsis: String?= null, // Gabii Braun and Falco Grice have been training their entire lives to inherit one of the seven titans under Marley's control and aid their nation in eradicating the Eldians on Paradis. However, just as all seems well for the two cadets, their peace is suddenly shaken by the arrival of Eren Yeager and the remaining members of the Survey Corps.Having finally reached the Yeager family basement and learned about the dark history surrounding the titans, the Survey Corps has at long last found the answer they so desperately fought to uncover. With the truth now in their hands, the group set out for the world beyond the walls.In Shingeki no Kyojin: The Final Season, two utterly different worlds collide as each party pursues its own agenda in the long-awaited conclusion to Paradis' fight for freedom.[Written by MAL Rewrite]
        @SerializedName("title")
        val title: String?= null, // Shingeki no Kyojin: The Final Season
        @SerializedName("type")
        val type: String?= null, // TV
        @SerializedName("url")
        val url: String?= null // https://myanimelist.net/anime/40028/Shingeki_no_Kyojin__The_Final_Season
    ) {
        @Keep
        data class Genre(
            @SerializedName("mal_id")
            val malId: Int?= null, // 1
            @SerializedName("name")
            val name: String?= null, // Action
            @SerializedName("type")
            val type: String?= null, // anime
            @SerializedName("url")
            val url: String?= null // https://myanimelist.net/anime/genre/1/Action
        )

        @Keep
        data class Producer(
            @SerializedName("mal_id")
            val malId: Int?= null, // 569
            @SerializedName("name")
            val name: String?= null, // MAPPA
            @SerializedName("type")
            val type: String?= null, // anime
            @SerializedName("url")
            val url: String ?= null // https://myanimelist.net/anime/producer/569/MAPPA
        )
    }
}