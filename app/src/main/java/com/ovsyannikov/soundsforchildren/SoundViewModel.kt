package com.ovsyannikov.soundsforchildren

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val soundsForChildren: SoundsForChildren) : BaseObservable() {
    fun onButtonClicked() {
        sound?.let {
            soundsForChildren.play(it)
        }
    }

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }
    @get:Bindable
    val title: String?
        get() = sound?.name
}