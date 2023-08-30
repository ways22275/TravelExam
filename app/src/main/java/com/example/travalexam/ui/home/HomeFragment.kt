package com.example.travalexam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.example.travalexam.data.Attraction
import com.example.travalexam.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  private val viewModel: HomeViewModel by viewModel()

  private val homeAttractionsAdapter: HomeAttractionsAdapter by lazy {
    HomeAttractionsAdapter(this::onItemClicked)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    sharedElementEnterTransition =
      TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    postponeEnterTransition()

    initAttractionsView()
    initSwipeRefreshLayout()

    observeAttractionSource()
    observeLanguageCodeSource()
  }

  private fun initAttractionsView() {
    with(binding.attractionsView) {
      doOnPreDraw {
        startPostponedEnterTransition()
      }
      layoutManager = LinearLayoutManager(requireContext())
      adapter = homeAttractionsAdapter
    }
  }

  private fun onItemClicked(attraction: Attraction) {
    findNavController().navigate(
      directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(attraction.id)
    )
  }

  private fun initSwipeRefreshLayout() {
    binding.swipeRefreshLayout.setOnRefreshListener {
      homeAttractionsAdapter.refresh()
    }
  }

  private fun observeAttractionSource() {
    homeAttractionsAdapter.addLoadStateListener { loadState ->
      when (loadState.refresh) {
        is LoadState.Loading -> binding.swipeRefreshLayout.isRefreshing = true
        else -> binding.swipeRefreshLayout.isRefreshing = false
      }
    }

    viewModel.attractionSource.observe(viewLifecycleOwner) {
      homeAttractionsAdapter.submitData(lifecycle, it)
    }
  }

  private fun observeLanguageCodeSource() {
    viewModel.currentLanguage.observe(viewLifecycleOwner) {
      // TODO : Switch language
    }
  }

}