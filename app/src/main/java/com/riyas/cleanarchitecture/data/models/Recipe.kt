package com.riyas.cleanarchitecture.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe (
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("name")
    val name: String="",
    @SerializedName("instructions")
    val instructions: List<String> = arrayListOf(),
    @SerializedName("tags")
    val tags: List<String> = arrayListOf(),
    @SerializedName("ingredients")
    val ingredients: List<String> = arrayListOf(),
    @SerializedName("prepTimeMinutes")
    val prepTimeMinutes: Int=0,
    @SerializedName("cookTimeMinutes")
    val cookTimeMinutes: Int=0,
    @SerializedName("servings")
    val servings: Int=0,
    @SerializedName("difficulty")
    val difficulty: String="",
    @SerializedName("cuisine")
    val cuisine: String="",
    @SerializedName("caloriesPerServing")
    val caloriesPerServing: Int=0,
    @SerializedName("rating")
    val rating:  Double=0.0,
    @SerializedName("reviewCount")
    val reviewCount: Double=0.0,
    @SerializedName("image")
    val image: String="",
): Parcelable