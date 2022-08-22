package jt.projects.gbmaterialapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import jt.projects.gbmaterialapp.BuildConfig
import jt.projects.gbmaterialapp.MainActivity
import jt.projects.gbmaterialapp.R
import jt.projects.gbmaterialapp.databinding.FragmentPictureOfTheDayBinding
import jt.projects.gbmaterialapp.model.dto.PODServerResponseData
import jt.projects.gbmaterialapp.ui.tools.BottomNavigationDrawerFragment
import jt.projects.gbmaterialapp.ui.tools.SettingsFragment
import jt.projects.gbmaterialapp.util.TAG
import jt.projects.gbmaterialapp.util.toast
import jt.projects.gbmaterialapp.viewmodel.PictureOfTheDayData
import jt.projects.gbmaterialapp.viewmodel.PictureOfTheDayViewModel
import java.time.LocalDate


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    //Определим переменную типа BottomSheetBehaviour. В качестве generic передаём тип контейнера
    //нашего BottomSheet. Этот instance будет управлять нашей нижней панелью.
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }

    //Ленивая инициализация модели
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getDate().observe(viewLifecycleOwner) { renderData(it) }
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        initBottomSheetListeners()
        initFabListener()
        initChipGroup()
        initChipHD()

        binding.wikiInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.wikiInputEditText.text.toString()}")
            })
        }
        setBottomAppBar()
    }

    private fun initChipHD() {
        binding.chipPhotoHd.setOnClickListener {
            viewModel.getDate().value?.let { renderData(it) }
        }
    }


    private fun initChipGroup() {
        binding.chipGroupPhotoDate.setOnCheckedChangeListener { chipGroup, position ->
            when (chipGroup.checkedChipId) {
                binding.chipToday.id -> {
                    viewModel.loadPictureOfTheDay()
                }
                binding.chipYesterday.id -> {
                    val yesterday = LocalDate.now().minusDays(1)
                    viewModel.loadPictureOfTheDayByDate(yesterday)
                }
                binding.chipDayBeforeYesterday.id -> {
                    val yesterday = LocalDate.now().minusDays(2)
                    viewModel.loadPictureOfTheDayByDate(yesterday)
                }
                else -> viewModel.loadPictureOfTheDay()
            }
        }
        binding.chipToday.isChecked = true
    }

    private fun initFabListener() {
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode =
                    BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hamburger_menu_bottom_bar
                    )
                binding.bottomAppBar.fabAlignmentMode =
                    BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    private fun setBottomAppBar() {
        val context = activity as MainActivity
        context.setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)// эта строчка говорит о том, что у фрагмента должен быть доступ к меню Активити
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }

            R.id.app_bar_theme -> (activity as MainActivity).showThemeDialog()

            R.id.app_bar_settings ->
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .add(R.id.container, SettingsFragment())
                        .addToBackStack(null).commit()
                }

            R.id.app_bot_sheet_expand -> {
                when (bottomSheetBehavior.state) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        bottomSheetBehavior.state =
                            BottomSheetBehavior.STATE_EXPANDED
                        setIconArrowDown()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottomSheetBehavior.state =
                            BottomSheetBehavior.STATE_EXPANDED
                        setIconArrowDown()
                    }
                    else -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        setIconArrowUp()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @Suppress("DEPRECATION")
    private fun setIconArrowDown() {
        val expand = binding.bottomAppBar.menu.findItem(R.id.app_bot_sheet_expand)
        expand.icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_down_24)
    }

    @Suppress("DEPRECATION")
    private fun setIconArrowUp() {
        val expand = binding.bottomAppBar.menu.findItem(R.id.app_bot_sheet_expand)
        expand.icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24)
    }

    private fun initBottomSheetListeners() {
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                var state = ""
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> state = "STATE_DRAGGING"
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        state = "STATE_COLLAPSED"
                        setIconArrowUp()
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        state = "STATE_EXPANDED"
                        setIconArrowDown()
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        state = "STATE_HALF_EXPANDED"
                        setIconArrowUp()
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        state = "STATE_HIDDEN"
                        setIconArrowUp()
                    }
                    BottomSheetBehavior.STATE_SETTLING -> state = "STATE_SETTLING"
                }
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, state)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // во время скольжения !
            }
        })
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state =
            BottomSheetBehavior.STATE_COLLAPSED//состояние (свёрнутое, но не скрытое)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val serverResponseData = data.serverResponseData
                var url: String?
                if (binding.chipPhotoHd.isChecked) {
                    url = serverResponseData.hdurl
                } else url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    //Coil в работе: достаточно вызвать у нашего ImageView нужную extension - функцию и передать ссылку на изображение
                    //а в лямбде указать дополнительные параметры (не обязательно) для отображения ошибки, процесса загрузки, анимации смены изображений
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                        crossfade(true)
                    }
                    renderBottomSheet(serverResponseData)
                }
            }
            is PictureOfTheDayData.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is PictureOfTheDayData.Error -> {
                binding.loadingLayout.visibility = View.GONE
                toast(data.error.message)
            }
        }
    }

    private fun renderBottomSheet(serverResponseData: PODServerResponseData) {
        (view?.findViewById(R.id.bottomSheetDescriptionHeader) as TextView).also {
            it.text = "${serverResponseData.title}"
        }
        (view?.findViewById(R.id.bottomSheetDescription) as TextView).also {
            with(serverResponseData)
            { it.text = "${explanation}\n\n©️${copyright} - ${date}" }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}