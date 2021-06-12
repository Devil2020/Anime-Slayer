package com.morse.animeslayer.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimeListResponse(
    @SerializedName("anime")
    val anime: List<Anime>,
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int, // 2103
    @SerializedName("request_cached")
    val requestCached: Boolean, // true
    @SerializedName("request_hash")
    val requestHash: String, // request:season:7bce9e37b2e6b6ca1ca3a2b9af1280ea8ee43aee
    @SerializedName("season_name")
    val seasonName: String, // Winter
    @SerializedName("season_year")
    val seasonYear: Int // 2021
) {
    @Keep
    data class Anime(
        @SerializedName("airing_start")
        val airingStart: String?, // 2020-12-06T15:10:00+00:00
        @SerializedName("continuing")
        val continuing: Boolean, // false
        @SerializedName("episodes")
        val episodes: Int?, // 16
        @SerializedName("genres")
        val genres: List<Genre>,
        @SerializedName("image_url")
        val imageUrl: String, // https://cdn.myanimelist.net/images/anime/1000/110531.jpg
        @SerializedName("kids")
        val kids: Boolean, // false
        @SerializedName("licensors")
        val licensors: List<String>,
        @SerializedName("mal_id")
        val malId: Int, // 40028
        @SerializedName("members")
        val members: Int, // 1123762
        @SerializedName("producers")
        val producers: List<Producer>,
        @SerializedName("r18")
        val r18: Boolean, // false
        @SerializedName("score")
        val score: Double?, // 8.99
        @SerializedName("source")
        val source: String, // Manga
        @SerializedName("synopsis")
        val synopsis: String, // Gabii Braun and Falco Grice have been training their entire lives to inherit one of the seven titans under Marley's control and aid their nation in eradicating the Eldians on Paradis. However, just as all seems well for the two cadets, their peace is suddenly shaken by the arrival of Eren Yeager and the remaining members of the Survey Corps.Having finally reached the Yeager family basement and learned about the dark history surrounding the titans, the Survey Corps has at long last found the answer they so desperately fought to uncover. With the truth now in their hands, the group set out for the world beyond the walls.In Shingeki no Kyojin: The Final Season, two utterly different worlds collide as each party pursues its own agenda in the long-awaited conclusion to Paradis' fight for freedom.[Written by MAL Rewrite]
        @SerializedName("title")
        val title: String, // Shingeki no Kyojin: The Final Season
        @SerializedName("type")
        val type: String, // TV
        @SerializedName("url")
        val url: String // https://myanimelist.net/anime/40028/Shingeki_no_Kyojin__The_Final_Season
    ) {
        @Keep
        data class Genre(
            @SerializedName("mal_id")
            val malId: Int, // 1
            @SerializedName("name")
            val name: String, // Action
            @SerializedName("type")
            val type: String, // anime
            @SerializedName("url")
            val url: String // https://myanimelist.net/anime/genre/1/Action
        )

        @Keep
        data class Producer(
            @SerializedName("mal_id")
            val malId: Int, // 569
            @SerializedName("name")
            val name: String, // MAPPA
            @SerializedName("type")
            val type: String, // anime
            @SerializedName("url")
            val url: String // https://myanimelist.net/anime/producer/569/MAPPA
        )
    }
}