package com.example.recyclerview

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private val activity: MainActivity, val items: MutableMap<String, String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    private var itemTitles : /*arrayOf("text1" , "text2")*/ArrayList<String> = initKeys()
    private var itemDefinition : /*arrayOf("text1" , "text2")*/ArrayList<String> = initDef()

    private val itemImages = intArrayOf(
            R.drawable.savoir ,
            R.drawable.maison,R.drawable.langage ,
            R.drawable.philosophie, R.drawable.internet ,
            R.drawable.enfant,R.drawable.stylo ,
            R.drawable.professeur, R.drawable.etudiant ,
            R.drawable.text,R.drawable.programmation ,
            R.drawable.mainframe, R.drawable.mainframe
    )


    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        var image : ImageView
        var textTitle : TextView
        var textDes : TextView

        init {
            image = itemView.findViewById(R.id.item_image)
            textTitle = itemView.findViewById(R.id.item_title)
            textDes = itemView.findViewById(R.id.item_definition)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    val v:View = LayoutInflater.from(parent.context)
        .inflate(R.layout.recyclerview_model,parent,false)
    return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = itemTitles[position]
        holder.textDes.text = itemDefinition[position]
        holder.image.setImageResource(itemImages[position])
        holder.textDes.setOnClickListener { v:View ->

            val fragmentB = FragB()
            val bundle = Bundle()
            bundle.putString("definition", itemDefinition[position])
            bundle.putString("title", itemTitles[position])

            bundle.putInt("image", itemImages[position])
            fragmentB.setArguments(bundle)
            val appCompatActivity = v.context as AppCompatActivity

            if (v.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                appCompatActivity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container2, fragmentB).addToBackStack(null)
                        .commit()
            } else {
                appCompatActivity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentB).addToBackStack(null)
                        .commit()
            }



        Toast.makeText(v.context, "${itemTitles[position]} has clicked",Toast.LENGTH_SHORT).show()
        }
    }
    private fun initKeys(): ArrayList<String>{
        var keys = ArrayList<String>()
        for (key in items.keys) {
            keys.add(key)
        }
        return keys
    }
    private fun initDef(): ArrayList<String>{
        var keysDef = ArrayList<String>()
        for (key in items.keys) {
            keysDef.add(items[key].toString())
        }
        return keysDef
    }

    override fun getItemCount(): Int {
        return itemTitles.size
    }

}
