package jt.projects.gbmaterialapp.ui.textstyles

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.style.*
import androidx.appcompat.app.AppCompatActivity
import jt.projects.gbmaterialapp.databinding.ActivityTextstylesBinding


class TextStylesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextstylesBinding

    //  у каждого view, в том числе, и TextView, есть свои методы жизненного цикла:
    //● onMeasure — получаем размеры view;
    //● onLayout — получаем расположение на экране;
    //● onDraw — получаем Canvas для отображения.


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextstylesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // html
        val text = "My text <ul><li>bullet one</li><li>bullet two</li></ul>"
        binding.htmlTextView.text = Html.fromHtml(text)

        //span
        //просто чтение/получение span’ов → SpannedString;
        //активная работа по изменению текста и span’ов, применение более 10 span’ов к тексту → SpannableStringBuilder;
        //активная работа по изменению текста и span’ов, применение менее 10 span’ов к тексту → SpannableString.

        // Использование флага SPAN_EXCLUSIVE_INCLUSIVE не включит пробел слева, но включит символ справа
        val spannable = SpannableString("Text is spantastic!")
        spannable.setSpan(ForegroundColorSpan(Color.RED), 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            8,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(URLSpan("https://ya.ru"), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(UnderlineSpan(), 10, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.spanTextView.text = spannable


        // paragraps span
        val spannable2 =
            SpannableString("Список\nраз\nдва\n")
        spannable2.setSpan(
            BulletSpan(20, Color.RED, 10),
            7, 10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable2.setSpan(
            BulletSpan(20, Color.RED, 10),
            11, spannable2.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.spanTextView2.text = spannable2

        paint()
    }


    private fun paint() {

    }


}