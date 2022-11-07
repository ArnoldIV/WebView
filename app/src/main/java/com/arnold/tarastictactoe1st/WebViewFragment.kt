package com.arnold.tarastictactoe1st



import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.arnold.tarastictactoe1st.databinding.FragmentWebviewBinding


class WebViewFragment : Fragment(R.layout.fragment_webview) {

    private lateinit var binding: FragmentWebviewBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentWebviewBinding.bind(view)


        binding.webView.apply {

            webViewClient = object : WebViewClient(){

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                    return super.shouldOverrideUrlLoading(view, url)
                }
            }

            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.domStorageEnabled = true
            //loadUrl(WebViewFragmentArgs.fromBundle(requireArguments()).url.trim())
            requireArguments().getString("url")?.let { loadUrl(it) }
            Log.d("webfragment", "$url")
        }


        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        super.setEnabled(false)
                    }

                }
            })

    }
}