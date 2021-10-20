package com.almogdadjabir.com.model.remote.verses

import kotlinx.serialization.Serializable

@Serializable
data class Verses (

	val id : Int?,
	val verse_key : String?,
	val text_uthmani : String?
)