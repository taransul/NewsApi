package com.example.newsapi.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentNewsBinding
import com.example.newsapi.presentation.NewsFragmentViewModel
import com.example.newsapi.presentation.recyclers.News
import com.example.newsapi.presentation.recyclers.NewsAdapter
import com.example.newsapi.presentation.recyclers.OnNewsClickListener
import com.example.newsapi.utils.openLink
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val adapter by lazy { NewsAdapter(newsClickListener) }
    private val binding: FragmentNewsBinding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel: NewsFragmentViewModel by viewModel()

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {

        override fun onIconClickListener(position: Int) {
            viewModel.onNewsItemClicked(position)
        }

        override fun onItemClickListener(news: News) {
            openLink(news.articleUrl)
        }
    }

    override fun onStart() {
        super.onStart()
        initRecycler()

        viewModel.newsNetwork.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }

    private fun initRecycler() {
        binding.recycleList.adapter = adapter
        binding.recycleList.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(NewsFragmentDirections.actionMainFragmentToSavedFragment())
        }
    }
}