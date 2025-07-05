package com.drygin.kinocopy.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drygin.kinocopy.R
import com.drygin.kinocopy.databinding.ItemFilmBinding
import com.drygin.kinocopy.features.home.domain.model.Film

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
class FilmAdapter(
    private val onFilmClick: (Film) -> Unit
) : ListAdapter<Film, FilmAdapter.FilmViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater, parent, false)
        return FilmViewHolder(binding, onFilmClick)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FilmViewHolder(
        private val binding: ItemFilmBinding,
        private val onClick: (Film) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) = with(binding) {
            titleTextView.text = film.localizedName

            Glide.with(posterImageView)
                .load(film.imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .centerCrop()
                .into(posterImageView)

            root.setOnClickListener { onClick(film) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(old: Film, new: Film): Boolean =
            old.id == new.id

        override fun areContentsTheSame(old: Film, new: Film): Boolean =
            old == new
    }
}


