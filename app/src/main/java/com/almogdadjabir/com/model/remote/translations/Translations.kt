package com.almogdadjabir.com.model.remote.translations

import kotlinx.serialization.Serializable

@Serializable
data class Translations (

	val resource_id : Int?,
	val text : String?
)