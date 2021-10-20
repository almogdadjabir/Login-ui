package com.almogdadjabir.com.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class APIQuranData (
 val chapters : List<Chapters>
)