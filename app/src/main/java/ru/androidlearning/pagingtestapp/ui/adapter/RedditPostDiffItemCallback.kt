package ru.androidlearning.pagingtestapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.pagingtestapp.domain.RedditPostInfo

object RedditPostDiffItemCallback : DiffUtil.ItemCallback<RedditPostInfo>() {

    override fun areItemsTheSame(oldItem: RedditPostInfo, newItem: RedditPostInfo): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RedditPostInfo, newItem: RedditPostInfo): Boolean {
        return oldItem == newItem
    }
}
