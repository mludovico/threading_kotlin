package br.com.mludovico.threading

import com.google.gson.annotations.SerializedName

data class AstroPeople(
    @SerializedName("craft")
    val craft: String,
    @SerializedName("name")
    val name: String
)
