package com.almogdadjabir.com.model.remote.aya

import com.almogdadjabir.com.model.remote.translations.Translations
import com.almogdadjabir.com.model.remote.verses.Verses
import kotlinx.serialization.Serializable

@Serializable
class Ayah (
    val verses : List<Verses>?,
    val  tranlations: List<Translations>?

)