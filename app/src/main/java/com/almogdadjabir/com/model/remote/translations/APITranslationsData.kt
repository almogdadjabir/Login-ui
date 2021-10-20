package com.almogdadjabir.com.model.remote.translations

import kotlinx.serialization.Serializable

@Serializable
data class APITranslationsData (

	val translations : List<Translations>?,
	val meta : Meta?
)