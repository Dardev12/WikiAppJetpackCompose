package com.dardev.wikiappcours.domain.model

data class Character(
    val id: String = "",
    val name: String = "",
    val picture: String = "",
    val about: String = "",
    val quote: String = "",
    val part: String = "",
    val strength: Int? = null,
    val intelligence: Int? = null,
    val hp: Int? = null,
    val dexterity: Int? = null,
    val arcane: Int? = null,
    val potential: Int? = null
)
