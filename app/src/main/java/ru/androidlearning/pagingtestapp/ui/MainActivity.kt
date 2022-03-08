package ru.androidlearning.pagingtestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import ru.androidlearning.pagingtestapp.R
import ru.androidlearning.pagingtestapp.databinding.ActivityMainBinding
import ru.androidlearning.pagingtestapp.ui.adapter.RedditPostsAdapter
import ru.androidlearning.pagingtestapp.ui.adapter.RedditPostsLoadStateAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main), AndroidScopeComponent {
    private val viewModel: MainViewModel by viewModel()
    override val scope: Scope by activityScope()
    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    private val redditPostsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        RedditPostsAdapter(this).apply {
            addLoadStateListener { state ->
                with(viewBinding) {
                    mainRv.isVisible = state.refresh != LoadState.Loading
                    progress.isVisible = state.refresh == LoadState.Loading
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        renderData()
    }

    private fun initRecyclerView() = with(viewBinding.mainRv) {
        adapter = redditPostsAdapter.withLoadStateFooter(
            footer = RedditPostsLoadStateAdapter()
        )
        addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun renderData() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.redditTopics
                    .collectLatest {
                        redditPostsAdapter.submitData(it)
                    }
            }
        }
    }
}
