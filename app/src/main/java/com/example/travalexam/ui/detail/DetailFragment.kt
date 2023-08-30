package com.example.travalexam.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.travalexam.data.Attraction
import com.example.travalexam.data.Image
import com.example.travalexam.databinding.FragmentDetailBinding
import com.example.travalexam.utils.SpacingItemDecorator
import com.example.travalexam.utils.bindImage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

  private var _binding: FragmentDetailBinding? = null
  private val binding get() = _binding!!

  private val viewModel: DetailViewModel by viewModel()

  private val adapter: ImagePagerAdapter = ImagePagerAdapter(this::onItemSelected)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    collectData()
    observeData()
    setupImageViews()
  }

  private fun collectData() {
    lifecycleScope.launch {
      viewModel.attractionFlow.collectLatest {
        val data = it ?: return@collectLatest
        viewModel.updateImages(data.images)
        setupAttractionInfo(data)
      }
    }
  }

  private fun observeData() {
    viewModel.images.observe(viewLifecycleOwner) {
      adapter.update(it)
      binding.imageView.bindImage(it.find { image: Image -> image.selected }?.src)
    }
  }

  private fun setupImageViews() {
    binding.imagesViews.adapter = this@DetailFragment.adapter
    binding.imagesViews.itemAnimator = null
    binding.imagesViews.addItemDecoration(SpacingItemDecorator(4))
  }

  private fun onItemSelected(position: Int, imageUrl: String) {
    binding.imageView.bindImage(imageUrl)
    binding.imagesViews.scrollToPosition(position)
    viewModel.syncImages(position)
  }

  private fun setupAttractionInfo(data: Attraction) {
    with(data) {
      binding.titleTextView.isSelected = true
      binding.titleTextView.text = name
      binding.introductionTextView.text = introduction
      binding.addressTextView.text = address
      binding.modifiedTimeTextView.text = modifiedTime
      binding.officialSiteTextView.text = officialSite
      binding.officialSiteTextView.setOnClickListener {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToWebViewFragment(officialSite))
      }
    }
  }
}