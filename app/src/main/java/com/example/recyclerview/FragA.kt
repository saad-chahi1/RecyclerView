package com.example.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.frag_a.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class FragA : Fragment() {

    private lateinit var layoutManager : RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**/

    }
    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {

            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        //layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        //adapter = generateListOfItems().let { RecyclerViewAdapter(it) }
        recyclerView.adapter = generateListOfItems().let { RecyclerViewAdapter(MainActivity(), it) }

        recyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                // Your logic
                Toast.makeText(view.context, "Pour voir les détails, cliquer sur la définition ", Toast.LENGTH_SHORT).show()
            }
        })

    }



    fun readTextFile(file_resource : Int) : ArrayList<String> {
        val result = ArrayList<String>()
        var string: String? = ""
        val `is`: InputStream = this.resources.openRawResource(file_resource)
        val reader = BufferedReader(InputStreamReader(`is`))
        while (true) {
            try {
                if (reader.readLine().also { string = it } == null) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            result.add(string.toString())
        }
    `is`.close()

    return result
    }

    private fun generateListOfItems(): MutableMap<String, String> {

        val lines: List<String> = readTextFile(R.raw.dictionnaire)
        val items = mutableMapOf<String, String>()

        lines.forEach { line ->
            run {
                val tab = line.split(':')
                if (tab.size >= 2)
                    items.put(tab[0], tab[1])
            }
        }
        return items
    }



}