package com.almogdadjabir.com.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class Translated_name (

 val language_name : String,
	val name : String
)