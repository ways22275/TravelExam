package com.example.travalexam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travalexam.R
import com.example.travalexam.data.Attraction
import com.example.travalexam.databinding.FragmentHomeBinding
import com.example.travalexam.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), Toolbar.OnMenuItemClickListener  {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  private val viewModel: HomeViewModel by viewModel()

  private val homeAttractionsAdapter: HomeAttractionsAdapter by lazy {
    HomeAttractionsAdapter(this::onItemClicked)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Utils.fullscreenBehindStatusBar(requireActivity().window, binding.root)
    WindowCompat.getInsetsController(requireActivity().window, view).isAppearanceLightStatusBars = false

    initAttractionsView()
    initSwipeRefreshLayout()
    initMenu()

    observeAttractionSource()
    observeLanguageCodeSource()
  }

  override fun onMenuItemClick(item: MenuItem): Boolean {
    when(item.itemId) {
      R.id.language -> {
        // TODO
      }
    }
    return false
  }

  private fun initAttractionsView() {
    with(binding.attractionsView) {
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

  private fun initMenu() {
    binding.toolbar.inflateMenu(R.menu.home_menu)
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
      homeAttractionsAdapter.refresh()
    }
  }

}