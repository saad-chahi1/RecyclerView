package com.example.recyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val manager = supportFragmentManager// Pour API >=19
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val trans = manager.beginTransaction()
        var frag1 = FragA()
        if(!frag1.isAdded) {
            trans.add(R.id.fragment_container, frag1)
            trans.commit()
        }*/
        this.configureAndShowMainFragment()
        this.configureAndShowDetailFragment()

    }

    private var detailFragment: FragB? = null
    private fun configureAndShowDetailFragment() {
        detailFragment = supportFragmentManager.findFragmentById(R.id.fragment_container2) as FragB?

        //A - We only add DetailFragment in Tablet mode (If found frame_layout_detail)
        if (detailFragment == null && findViewById<View?>(R.id.fragment_container2) != null) {
            detailFragment = FragB()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container2, detailFragment!!)
                    .commit()
        }
    }
    private var mainFragment: FragA? = null
    private fun configureAndShowMainFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        mainFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as FragA?
        if (mainFragment == null) {
            // B - Create new main fragment
            mainFragment = FragA()
            // C - Add it to FrameLayout container
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mainFragment!!)
                    .commit()
        }
    }

}