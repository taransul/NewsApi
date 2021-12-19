package com.example.newsapi.presentation.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentNewsBinding
import com.example.newsapi.network.dto.Article
import com.example.newsapi.presentation.NewsFragmentViewModel
import com.example.newsapi.presentation.recyclers.news.NewsAdapter
import com.example.newsapi.presentation.recyclers.news.OnNewsClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val adapter by lazy { NewsAdapter(newsClickListener) }
    private val viewModel: NewsFragmentViewModel by viewModel()
    private val binding: FragmentNewsBinding by viewBinding(FragmentNewsBinding::bind)

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {

        override fun onIconClickListener(position: Int, activated: Boolean) {
            viewModel.onNewsItemClicked(position, activated)
        }

        override fun <T> onItemClickListener(news: T) {
            news as Article
            Toast.makeText(
                context,
                news.title,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        initRecycler()

        viewModel.news.observe(viewLifecycleOwner) { news ->
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