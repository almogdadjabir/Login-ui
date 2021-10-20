package com.almogdadjabir.com.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?,
    val name: String?
)