package jt.projects.gbmaterialapp.ui.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbmaterialapp.databinding.ActivityRecyclerBinding

/**
 * имейте в виду, что RecyclerView плохо работает со ScrollView, потому что оба макета
«слушают» пользовательские свайпы и пытаются прокрутить контент. Тут есть три совета общего
характера:
1. Всегда используйте NestedScrollView вместо обычного ScrollView. Это более
современный макет;
2. Избегайте ситуации, когда у вас RecyclerView обёрнут в ScrollView и наоборот;
3. Если этого не избежать, нужно переопределять методы свайпа и самому решать, когда какой
layout будет реагировать на свайп. Но это уже плохая идея.

 */
class RecViewActivity : AppCompatActivity() {
    private var _binding: ActivityRecyclerBinding? = null
    private val binding get() = _binding!!

    lateinit var itemTouchHelper: ItemTouchHelper//  для перетаскивания за "ручку"


    val data : ArrayList<Pair<Data, Boolean>> = arrayListOf(
        Pair(Data(Data.TYPE_HEADER, "Hello, world!"), false),
        Pair(Data(Data.TYPE_EARTH, "Earth"),false),
        Pair(Data(Data.TYPE_MARS, "Mars", "HelloHelloHello"),false)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RecyclerActivityAdapter(
            object : OnListItemClickListener {
                override fun onItemClick(data: Data) {
                    Toast.makeText(
                        this@RecViewActivity, data.someText,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            },
            data
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener { adapter.appendItem() }
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}