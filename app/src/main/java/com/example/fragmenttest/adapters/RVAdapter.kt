package com.example.fragmenttest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttest.BrowserFragment
import com.example.fragmenttest.MainActivity
import com.example.fragmenttest.databinding.ItemRowBinding
import com.example.fragmenttest.retrofit.TvShows


class RVAdapter(
    private val entries: ArrayList<TvShows.TvShowsItem>,
    private val activity: BrowserFragment

) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entry = entries[position]!!

        holder.binding.apply {
            btItem.text = entry.show.name
            btItem.setOnClickListener {

                if (entry.show.image == null && entry.show.summary == null) {
                    activity.addShow(
                        entry.show.name,
                        entry.show.language,
                        "no summary",
                        ""
                    )
                } else if (entry.show.image == null) {
                    activity.addShow(
                        entry.show.name,
                        entry.show.language,
                        entry.show.summary!!,
                         ""
                    )
                }
                else if(entry.show.summary == null){
                    activity.addShow(
                        entry.show.name,
                        entry.show.language,
                        "no summary",
                        entry.show.image!!.original
                    )
                }
                else if(entry.show.language == null){
                    activity.addShow(
                        entry.show.name,
                       "not defined",
                        entry.show.summary!!,
                        entry.show.image!!.original
                    )
                }
                else{
                    activity.addShow(
                        entry.show.name,
                        entry.show.language,
                        entry.show.summary,
                        entry.show.image!!.original
                    )
                }
            }
            Toast.makeText(activity.activity, "Show added", Toast.LENGTH_LONG).show()
        }
    }

override fun getItemCount() = entries.size

}