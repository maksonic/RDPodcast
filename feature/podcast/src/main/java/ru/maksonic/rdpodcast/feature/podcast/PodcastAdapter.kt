package ru.maksonic.rdpodcast.feature.podcast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.maksonic.rdpodcast.core.ui.click
import ru.maksonic.rdpodcast.feature.podcast.databinding.ItemPodcastBinding
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi

/**
 * @Author: maksonic on 09.02.2022
 */
class PodcastAdapter(
    private val onClick: (PodcastUi?) -> Unit,
    private val onMoreClick: (PodcastUi?) -> Unit
) : ListAdapter<PodcastUi, PodcastAdapter.PodcastViewHolder>(PodcastItemDiffUtil()) {
    private var selectedPodcast = 0

    inner class PodcastViewHolder(
        private val binding: ItemPodcastBinding,
        private val onClick: (PodcastUi?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentPodcast: PodcastUi? = null

        init {
            binding.podcastItem.apply {
                click {
                    onClick.invoke(currentPodcast)
                    setSelectedItemBackgroundColor()
                    if (selectedPodcast != adapterPosition) {
                        notifyItemChanged(selectedPodcast)
                        selectedPodcast = adapterPosition
                    }
                }
                setOnLongClickListener {
                    onMoreClick.invoke(currentPodcast)
                    return@setOnLongClickListener true
                }
            }
            binding.btnMore.click {
                onMoreClick.invoke(currentPodcast)
            }
        }

        fun bind(podcast: PodcastUi) {
            currentPodcast = podcast
            if (selectedPodcast == -1) {
                setUnSelectedItemBackgroundColor()
            } else {
                if (selectedPodcast == adapterPosition) {
                   setSelectedItemBackgroundColor()
                } else {
                    setUnSelectedItemBackgroundColor()
                }
            }
            with(binding) {
                setUnSelectedItemBackgroundColor()
                txtPodcastName.text = podcast.name
                Glide.with(imgPodcast)
                    .load(podcast.image)
                    .placeholder(R.drawable.podcast_image)
                    .error(R.drawable.podcast_image)
                    .apply(RequestOptions().override(100, 100))
                    .into(imgPodcast)
            }
        }

        private fun setSelectedItemBackgroundColor() {
            binding.podcastItem.setBackgroundColor(
                ContextCompat.getColor(binding.podcastItem.context, R.color.color_secondary_variant)
            )
        }
        private fun setUnSelectedItemBackgroundColor() {
            binding.podcastItem.setBackgroundColor(ContextCompat.getColor(
                binding.podcastItem.context, R.color.background))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        return PodcastViewHolder(
            ItemPodcastBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val podcast = getItem(position)
        holder.bind(podcast)
    }
}

class PodcastItemDiffUtil : DiffUtil.ItemCallback<PodcastUi>() {
    override fun areItemsTheSame(oldItem: PodcastUi, newItem: PodcastUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PodcastUi, newItem: PodcastUi): Boolean {
        return oldItem == newItem
    }
}
