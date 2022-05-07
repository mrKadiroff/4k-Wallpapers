package com.example.a4kwallpapers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4kwallpapers.databinding.PhotoItemBinding
import com.example.a4kwallpapers.models2.Hit
import com.squareup.picasso.Picasso

class PhotoAdapter (var list: List<Hit>) : RecyclerView.Adapter<PhotoAdapter.Vh>() {

    inner class Vh(var photoItemBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(photoItemBinding.root){

        fun onBind(hit: Hit) {
            Picasso.get().load(hit.largeImageURL).into(photoItemBinding.imageUri)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(PhotoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}