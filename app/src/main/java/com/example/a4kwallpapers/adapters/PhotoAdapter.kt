package com.example.a4kwallpapers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a4kwallpapers.databinding.PhotoItemBinding
import com.example.a4kwallpapers.models2.Hit
import com.squareup.picasso.Picasso

class PhotoAdapter(var onItemClickListener: OnItemClickListener) : PagingDataAdapter<Hit, PhotoAdapter.Vh>(MyDiffutil()) {

    inner class Vh(var photoItemBinding: PhotoItemBinding) : RecyclerView.ViewHolder(photoItemBinding.root) {

        fun onBind(hit: Hit?){
            photoItemBinding.apply {
                Picasso.get().load(hit!!.largeImageURL)
                    .into(photoItemBinding.rasm)

                photoItemBinding.root.setOnClickListener {
                    onItemClickListener.onItemClick(hit)
                }

            }
        }

    }

    class MyDiffutil : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(PhotoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    interface OnItemClickListener{
        fun onItemClick(hit: Hit?)
    }
}