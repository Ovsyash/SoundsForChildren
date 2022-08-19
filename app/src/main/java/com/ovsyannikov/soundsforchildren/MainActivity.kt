package com.ovsyannikov.soundsforchildren

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ovsyannikov.soundsforchildren.databinding.ActivityMainBinding
import com.ovsyannikov.soundsforchildren.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var soundsForChildren: SoundsForChildren

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        soundsForChildren = SoundsForChildren(assets)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(soundsForChildren.sounds)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundsForChildren.release()
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.viewModel = SoundViewModel(soundsForChildren)
            }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {

            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount() = sounds.size


    }
}