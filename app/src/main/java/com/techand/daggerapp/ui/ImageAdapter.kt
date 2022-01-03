package com.techand.daggerapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.techand.daggerapp.data.models.Image
import com.techand.daggerapp.databinding.ImageLayoutBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private val DiffUtilCallBack = object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.path == newItem.path
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.path == newItem.path && oldItem.path == newItem.path
        }

    }
    private val differ = AsyncListDiffer(this, DiffUtilCallBack)
    var images: List<Image>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = images[position]

        holder.binding.apply {
            textView.text = currentImage.desc
            imageView.load(currentImage.path) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToDetailFragment(currentImage)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }


}
