package ru.androidlearning.pagingtestapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.pagingtestapp.R
import ru.androidlearning.pagingtestapp.databinding.ItemReggitPostBinding
import ru.androidlearning.pagingtestapp.domain.RedditPostInfo

class RedditPostsAdapter(context: Context) :
    PagingDataAdapter<RedditPostInfo, RedditPostsAdapter.RedditPostsViewHolder>(RedditPostDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostsViewHolder {
        return RedditPostsViewHolder(layoutInflater.inflate(R.layout.item_reggit_post, parent, false))
    }

    override fun onBindViewHolder(holder: RedditPostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RedditPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding by viewBinding(ItemReggitPostBinding::bind)

        fun bind(redditPost: RedditPostInfo?) = with(viewBinding) {
            title.text = redditPost?.title.orEmpty()
            scoreTv.text = redditPost?.score.toString()
            messagesTv.text = redditPost?.numComments.toString()
        }
    }
}
