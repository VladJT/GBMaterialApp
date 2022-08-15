package jt.projects.gbmaterialapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import jt.projects.gbmaterialapp.R
import jt.projects.gbmaterialapp.databinding.FragmentPictureOfTheDayBinding
import jt.projects.gbmaterialapp.model.dto.PODServerResponseData
import jt.projects.gbmaterialapp.viewmodel.PictureOfTheDayData
import jt.projects.gbmaterialapp.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    //Определим переменную типа BottomSheetBehaviour. В качестве generic передаём тип контейнера
    //нашего BottomSheet. Этот instance будет управлять нашей нижней панелью.
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }


    //Ленивая инициализация модели
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        binding.wikiInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.wikiInputEditText.text.toString()}")
            })
        }
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
                val url = serverResponseData.url
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


    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}