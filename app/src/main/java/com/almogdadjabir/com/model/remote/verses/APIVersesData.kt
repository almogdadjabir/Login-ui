package com.almogdadjabir.com.model.remote.verses

import kotlinx.serialization.Serializable

@Serializable
data class APIVersesData (
	val verses : List<Verses>?
)