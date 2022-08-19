package com.ovsyannikov.soundsforchildren

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {

    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel
    private lateinit var soundsForChildren: SoundsForChildren

    @Before
    fun setUp() {
        soundsForChildren = mock(SoundsForChildren::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(soundsForChildren)
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(subject.title, `is`(sound.name))
    }

    @Test
    fun callsSoundForChildrenPlayOnButtonClicked() {
        subject.onButtonClicked()

        verify(soundsForChildren).play(sound)
    }
}