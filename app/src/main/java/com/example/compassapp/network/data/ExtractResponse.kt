package com.example.compassapp.network.data

import com.google.gson.annotations.SerializedName

data class ExtractResponse(@SerializedName("characters") val characters : List<String> )
