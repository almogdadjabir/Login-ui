package com.almogdadjabir.com.model.remote.translations

import kotlinx.serialization.Serializable

@Serializable
data class Meta (

	val translation_name : String?,
	val author_name : String?
)