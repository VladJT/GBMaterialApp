package jt.projects.gbmaterialapp.ui.viewpager

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import jt.projects.gbmaterialapp.databinding.FragmentEarthBinding
import jt.projects.gbmaterialapp.util.snackBar
import jt.projects.gbmaterialapp.util.toast

private const val rotation = "rotation"
private const val translationY = "translationY"
private const val rotationFrom = 0f
private const val rotationTo = 225f
private const val translationOneContainer = -130f
private const val translationTwoContainer = -250f
private const val translationPlusImageFrom = 0f
private const val translationPlusImageTo = -180f
private const val translationZero = 0f


/** ObjectAnimator и View.animate
 * такой способ анимации объектов устарел: он очень многословен, всё нужно делать вручную.
Но он всё ещё не потерял своей эффективности и, возможно, ещё сможет вам пригодиться для
каких-то простых анимаций или поддержки старых проектов.
 */
class EarthFragment : Fragment() {
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    private var isExpanded = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFAB()
//        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
//            binding.toolbar.isSelected =
//                binding.scrollView.canScrollVertically(-1)
//        }

    }

    private fun setFAB() {
        setInitialState()
        binding.fab.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFAB()
            }
        }
    }

    private fun setInitialState() {
        binding.transparentBackground.apply {
            alpha = 0f
        }
        binding.optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }

    private fun expandFAB() {
        isExpanded = true
        ObjectAnimator.ofFloat(
            binding.plusImageview, rotation, rotationFrom,
            rotationTo
        ).start()
        ObjectAnimator.ofFloat(
            binding.optionTwoContainer, translationY,
            translationTwoContainer
        ).start()
        ObjectAnimator.ofFloat(
            binding.optionOneContainer, translationY,
            translationOneContainer
        ).start()
        binding.optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        snackBar("Option 2")
                    }
                }
            })
        binding.optionOneContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        snackBar("Option 1")
                    }
                }
            })
        binding.transparentBackground.animate()
            .alpha(0.9f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = true
                }
            })
    }

    private fun collapseFab() {
        isExpanded = false
        ObjectAnimator.ofFloat(
            binding.plusImageview, rotation,
            translationPlusImageFrom, translationPlusImageTo
        ).start()
        ObjectAnimator.ofFloat(
            binding.optionTwoContainer, translationY,
            translationZero
        ).start()
        ObjectAnimator.ofFloat(
            binding.optionOneContainer, translationY,
            translationZero
        ).start()
        binding.optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                    binding.optionOneContainer.setOnClickListener(null)
                }
            })
        binding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                }
            })
        binding.transparentBackground.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = false
                }
            })
    }

}
