package com.ovsyannikov.soundsforchildren

private const val mp3 = ".mp3"

class Sound(val assetPath: String) {

    val name = assetPath.split("/").last().removeSuffix(mp3)
}