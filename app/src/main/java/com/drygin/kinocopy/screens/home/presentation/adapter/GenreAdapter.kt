package com.drygin.kinocopy.screens.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drygin.kinocopy.R
import com.drygin.kinocopy.common.domain.model.Genre
import com.drygin.kinocopy.databinding.ItemGenreBinding

/**
 * Created by Drygin Nikita on 03.07.2025.
 */
class GenreAdapter(
    private val onGenreClick: (String) -> Unit
) : ListAdapter<Genre, GenreAdapter.GenreViewHolder>(GenreDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(inflater, parent, false)
        return GenreViewHolder(binding, onGenreClick)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GenreViewHolder(
        private val binding: ItemGenreBinding,
        private val onClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Genre)  = with(binding) {
            genreTextView.text = item.name
            val backgroundColor = ContextCompat.getColor(
                root.context,
                if (item.isSelected) R.color.colorSecondary else R.color.colorBackground
            )
            with(genreContainer) {
                setBackgroundColor(backgroundColor)
                setOnClickListener { onClick(item.name) }
            }
        }
    }

    object GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(old: Genre, new: Genre): Boolean =
            old.name == new.name

        override fun areContentsTheSame(old: Genre, new: Genre): Boolean =
            old == new
    }
}
