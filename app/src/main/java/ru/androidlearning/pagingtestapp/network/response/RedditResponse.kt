package ru.androidlearning.pagingtestapp.network.response

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("after") val after: String,
    @SerializedName("dist") val dist: Int,
    @SerializedName("modhash") val modhash: String,
    @SerializedName("geo_filter") val geo_filter: String,
    @SerializedName("children") val children: List<Children>,
    @SerializedName("before") val before: String
)

data class Children(

    @SerializedName("kind") val kind: String,
    @SerializedName("data") val data: ChildrenData
)

data class ChildrenData(
    @SerializedName("title") val title: String,
    @SerializedName("score") val score: Int,
    @SerializedName("num_comments") val numComments: Int,
)
