package com.example.moviesapp_saeedhonari.ui.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.moviesapp_saeedhonari.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel
    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activityScope.launch {
            delay(1000)
            NavHostFragment.findNavController(this@SplashFragment)
                .navigate(R.id.action_splashFragment_to_moviesListFragment)
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }


}