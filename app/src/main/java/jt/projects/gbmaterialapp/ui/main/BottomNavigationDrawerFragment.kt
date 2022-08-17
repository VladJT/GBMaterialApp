package jt.projects.gbmaterialapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jt.projects.gbmaterialapp.R
import jt.projects.gbmaterialapp.databinding.BottomNavigationLayoutBinding
import jt.projects.gbmaterialapp.util.toast


class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> toast("1")
                R.id.navigation_two -> toast("2")
            }
            true

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}