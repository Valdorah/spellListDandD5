package com.valdorah.mangareader

data class Spell(
    var name: String,
    var url: String,
    var level: Int = 0,
    var desc: String = "",
    var school: String = ""
)