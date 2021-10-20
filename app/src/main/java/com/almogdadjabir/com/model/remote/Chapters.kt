package com.almogdadjabir.com.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class Chapters (

 val id : Int,
 val revelation_place : String,
 val revelation_order : Int,
 val bismillah_pre : Boolean,
 val name_simple : String,
 val name_complex : String,
 val name_arabic : String,
 val verses_count : Int,
 val pages : List<Int>,
 val translated_name : Translated_name
)