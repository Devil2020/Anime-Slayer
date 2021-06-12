package com.morse.animeslayer.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimeDetailResponse(
    @SerializedName("aired")
    val aired: Aired,
    @SerializedName("airing")
    val airing: Boolean, // false
    @SerializedName("background")
    val background: Any?, // null
    @SerializedName("broadcast")
    val broadcast: String, // Mondays at 00:10 (JST)
    @SerializedName("duration")
    val duration: String, // 23 min per ep
    @SerializedName("ending_themes")
    val endingThemes: List<String>,
    @SerializedName("episodes")
    val episodes: Int, // 16
    @SerializedName("favorites")
    val favorites: Int, // 44133
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("image_url")
    val imageUrl: String, // https://cdn.myanimelist.net/images/anime/1000/110531.jpg
    @SerializedName("licensors")
    val licensors: List<Licensor>,
    @SerializedName("mal_id")
    val malId: Int, // 40028
    @SerializedName("members")
    val members: Int, // 1125924
    @SerializedName("opening_themes")
    val openingThemes: List<String>,
    @SerializedName("popularity")
    val popularity: Int, // 74
    @SerializedName("premiered")
    val premiered: String, // Winter 2021
    @SerializedName("producers")
    val producers: List<Producer>,
    @SerializedName("rank")
    val rank: Int, // 11
    @SerializedName("rating")
    val rating: String, // R - 17+ (violence & profanity)
    @SerializedName("related")
    val related: Related,
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int, // 85946
    @SerializedName("request_cached")
    val requestCached: Boolean, // true
    @SerializedName("request_hash")
    val requestHash: String, // request:anime:929520a76c6b1167b54e1067860ebb44f7d94b3a
    @SerializedName("score")
    val score: Double, // 8.99
    @SerializedName("scored_by")
    val scoredBy: Int, // 631196
    @SerializedName("source")
    val source: String, // Manga
    @SerializedName("status")
    val status: String, // Finished Airing
    @SerializedName("studios")
    val studios: List<Studio>,
    @SerializedName("synopsis")
    val synopsis: String, // Gabii Braun and Falco Grice have been training their entire lives to inherit one of the seven titans under Marley's control and aid their nation in eradicating the Eldians on Paradis. However, just as all seems well for the two cadets, their peace is suddenly shaken by the arrival of Eren Yeager and the remaining members of the Survey Corps. Having finally reached the Yeager family basement and learned about the dark history surrounding the titans, the Survey Corps has at long last found the answer they so desperately fought to uncover. With the truth now in their hands, the group set out for the world beyond the walls. In Shingeki no Kyojin: The Final Season, two utterly different worlds collide as each party pursues its own agenda in the long-awaited conclusion to Paradis' fight for freedom. [Written by MAL Rewrite]
    @SerializedName("title")
    val title: String, // Shingeki no Kyojin: The Final Season
    @SerializedName("title_english")
    val titleEnglish: String, // Attack on Titan Final Season
    @SerializedName("title_japanese")
    val titleJapanese: String, // 進撃の巨人 The Final Season
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String>,
    @SerializedName("trailer_url")
    val trailerUrl: String, // https://www.youtube.com/embed/SlNpRThS9t8?enablejsapi=1&wmode=opaque&autoplay=1
    @SerializedName("type")
    val type: String, // TV
    @SerializedName("url")
    val url: String // https://myanimelist.net/anime/40028/Shingeki_no_Kyojin__The_Final_Season
) {
    @Keep
    data class Aired(
        @SerializedName("from")
        val from: String, // 2020-12-07T00:00:00+00:00
        @SerializedName("prop")
        val prop: Prop,
        @SerializedName("string")
        val string: String, // Dec 7, 2020 to Mar 29, 2021
        @SerializedName("to")
        val to: String // 2021-03-29T00:00:00+00:00
    ) {
        @Keep
        data class Prop(
            @SerializedName("from")
            val from: From,
            @SerializedName("to")
            val to: To
        ) {
            @Keep
            data class From(
                @SerializedName("day")
                val day: Int, // 7
                @SerializedName("month")
                val month: Int, // 12
                @SerializedName("year")
                val year: Int // 2020
            )

            @Keep
            data class To(
                @SerializedName("day")
                val day: Int, // 29
                @SerializedName("month")
                val month: Int, // 3
                @SerializedName("year")
                val year: Int // 2021
            )
        }
    }

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
    data class Licensor(
        @SerializedName("mal_id")
        val malId: Int, // 102
        @SerializedName("name")
        val name: String, // Funimation
        @SerializedName("type")
        val type: String, // anime
        @SerializedName("url")
        val url: String // https://myanimelist.net/anime/producer/102/Funimation
    )

    @Keep
    data class Producer(
        @SerializedName("mal_id")
        val malId: Int, // 10
        @SerializedName("name")
        val name: String, // Production I.G
        @SerializedName("type")
        val type: String, // anime
        @SerializedName("url")
        val url: String // https://myanimelist.net/anime/producer/10/Production_IG
    )

    @Keep
    data class Related(
        @SerializedName("Adaptation")
        val adaptation: List<Adaptation>,
        @SerializedName("Prequel")
        val prequel: List<Prequel>,
        @SerializedName("Sequel")
        val sequel: List<Sequel>
    ) {
        @Keep
        data class Adaptation(
            @SerializedName("mal_id")
            val malId: Int, // 23390
            @SerializedName("name")
            val name: String, // Shingeki no Kyojin
            @SerializedName("type")
            val type: String, // manga
            @SerializedName("url")
            val url: String // https://myanimelist.net/manga/23390/Shingeki_no_Kyojin
        )

        @Keep
        data class Prequel(
            @SerializedName("mal_id")
            val malId: Int, // 38524
            @SerializedName("name")
            val name: String, // Shingeki no Kyojin Season 3 Part 2
            @SerializedName("type")
            val type: String, // anime
            @SerializedName("url")
            val url: String // https://myanimelist.net/anime/38524/Shingeki_no_Kyojin_Season_3_Part_2
        )

        @Keep
        data class Sequel(
            @SerializedName("mal_id")
            val malId: Int, // 48583
            @SerializedName("name")
            val name: String, // Shingeki no Kyojin: The Final Season Part 2
            @SerializedName("type")
            val type: String, // anime
            @SerializedName("url")
            val url: String // https://myanimelist.net/anime/48583/Shingeki_no_Kyojin__The_Final_Season_Part_2
        )
    }

    @Keep
    data class Studio(
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