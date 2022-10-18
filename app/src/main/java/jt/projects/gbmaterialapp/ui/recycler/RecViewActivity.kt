package jt.projects.gbmaterialapp.ui.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jt.projects.gbmaterialapp.databinding.ActivityRecyclerBinding


class RecViewActivity : AppCompatActivity() {
    private var _binding: ActivityRecyclerBinding? = null
    private val binding get() = _binding!!

    val data = arrayListOf(
        Data(Data.TYPE_HEADER, "Hello, world!"),
        Data(Data.TYPE_EARTH, "Earth"),
        Data(Data.TYPE_MARS, "Mars", "HelloHelloHello")
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
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}