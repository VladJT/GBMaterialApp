package jt.projects.gbmaterialapp.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import jt.projects.gbmaterialapp.R
import jt.projects.gbmaterialapp.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private var show = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backgroundImage.setOnClickListener {
            if (show) hideComponents() else
                showComponents()
        }
    }

    private fun showComponents() {
        show = true
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_weather_end)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(
            binding.constraintContainer,
            transition
        )
        constraintSet.applyTo(binding.constraintContainer)
    }

    private fun hideComponents() {
        show = false
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_weather_end)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(
            binding.constraintContainer,
            transition
        )
        constraintSet.applyTo(binding.constraintContainer)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}