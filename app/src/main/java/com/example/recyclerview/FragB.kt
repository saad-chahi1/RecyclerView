package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class FragB : Fragment() {
    lateinit var textview : TextView
    lateinit var textView2 : TextView
    lateinit var imageView : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.frag_b, container, false)
        textview = view.findViewById<TextView>(R.id.textview)
        textView2 = view.findViewById<TextView>(R.id.textview2)
        imageView = view.findViewById<ImageView>(R.id.imageView)
        return view
    }
    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            val definition = arguments["definition"].toString()
            val title = arguments["title"].toString()
            val source = arguments["image"]
            textview.setText(definition)
            textView2.setText(title)
            imageView.setImageResource(source as Int)
        }
    }

}