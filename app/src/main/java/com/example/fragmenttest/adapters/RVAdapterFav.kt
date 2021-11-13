package com.example.fragmenttest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragmenttest.FavoriteFragment
import com.example.fragmenttest.R
import com.example.fragmenttest.database.Entity
import com.example.fragmenttest.databinding.FavRowBinding

class RVAdapterFav(private val activity: FavoriteFragment) : RecyclerView.Adapter<RVAdapterFav.ItemViewHolder>() {
    private var shows = emptyList<Entity>()
    class ItemViewHolder(val binding: FavRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            FavRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val show = shows[position]

        holder.binding.apply {
            tvName.text = show.showName
            tvLanguage.text = show.language

            if(show.image.isNotEmpty()){
                Glide.with(activity).load(show.image).into(imageView)
            }
           else{
               imageView.setImageResource(R.drawable.blank)
            }

            btDelete.setOnClickListener {
                activity.viewModel.deleteShow(show.id)
                Toast.makeText(activity.activity,"the show is deleted",Toast.LENGTH_LONG).show()

            }
            layout.setOnClickListener {
                   Toast.makeText(activity.activity,"${show.summary}",Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun getItemCount() = shows.size

    fun update(shows: List<Entity>){
        println("UPDATING DATA")
        this.shows = shows
        notifyDataSetChanged()
    }
}