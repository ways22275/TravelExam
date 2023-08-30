package com.example.travalexam.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.travalexam.databinding.FragmentWebViewBinding
import com.example.travalexam.utils.Utils

class WebViewFragment : Fragment() {

  private var _binding: FragmentWebViewBinding? = null
  private val binding get() = _binding!!

  private val args: WebViewFragmentArgs by navArgs()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentWebViewBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Utils.fullscreenBehindStatusBar(requireActivity().window, binding.root)
    WindowCompat.getInsetsController(requireActivity().window, view).isAppearanceLightStatusBars = true

    setupBackPressedListener()
    setupWebView()
  }

  private fun setupBackPressedListener() {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        if (binding.webView.canGoBack()) {
          binding.webView.goBack()
        } else {
          findNavController().popBackStack()
        }
      }
    })
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setupWebView() {
    binding.webView.settings.javaScriptEnabled = true
    binding.webView.loadUrl(args.url)
  }
}