package com.morse.animeslayer.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimeCharactersResponse(
    @SerializedName("characters")
    val characters: List<Character>,
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int, // 31805
    @SerializedName("request_cached")
    val requestCached: Boolean, // true
    @SerializedName("request_hash")
    val requestHash: String, // request:anime:b716caa2f73812e089a24ff0fa7cf43505f62362
    @SerializedName("staff")
    val staff: List<Staff>
) {
    @Keep
    data class Character(
        @SerializedName("image_url")
        val imageUrl: String ?= null, // https://cdn.myanimelist.net/images/characters/14/177967.jpg?s=613b9d9167b4775b2ffbb6385adf5b9d
        @SerializedName("mal_id")
        val malId: Int?= null, // 65407
        @SerializedName("name")
        val name: String?= null, // He Liao, Diao
        @SerializedName("role")
        val role: String?= null, // Main
        @SerializedName("url")
        val url: String?= null, // https://myanimelist.net/character/65407/Diao_He_Liao
        @SerializedName("voice_actors")
        val voiceActors: List<VoiceActor> ?= null
    ) {
        @Keep
        data class VoiceActor(
            @SerializedName("image_url")
            val imageUrl: String, // https://cdn.myanimelist.net/r/42x62/images/voiceactors/3/63374.jpg?s=b834d16ead8ec560bcfcbc2da93ae6a2
            @SerializedName("language")
            val language: String, // Japanese
            @SerializedName("mal_id")
            val malId: Int, // 8
            @SerializedName("name")
            val name: String, // Kugimiya, Rie
            @SerializedName("url")
            val url: String // https://myanimelist.net/people/8/Rie_Kugimiya
        )
    }

    @Keep
    data class Staff(
        @SerializedName("image_url")
        val imageUrl: String, // https://cdn.myanimelist.net/images/questionmark_23.gif?s=f7dcbc4a4603d18356d3dfef8abd655c
        @SerializedName("mal_id")
        val malId: Int, // 16373
        @SerializedName("name")
        val name: String, // Imaizumi, Kenichi
        @SerializedName("positions")
        val positions: List<String>,
        @SerializedName("url")
        val url: String // https://myanimelist.net/people/16373/Kenichi_Imaizumi
    )
}