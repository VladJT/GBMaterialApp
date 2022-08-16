package jt.projects.gbmaterialapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import jt.projects.gbmaterialapp.databinding.FragmentSettingsBinding
import jt.projects.gbmaterialapp.util.snackBar

class ChipsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                snackBar("Выбран ${it.text}")
            }
        }

        binding.chipClose.setOnCloseIconClickListener {
            snackBar("Close is Clicked")
        }
    }

    companion object {
        fun newInstance() = ChipsFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}