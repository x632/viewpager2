package com.poema.viewpager

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity(){

    private val sliderHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPagerImageSlider)

        val sliderItems = ArrayList<SliderItem>()
        sliderItems.add(SliderItem(R.drawable.img1))
        sliderItems.add(SliderItem(R.drawable.img2))
        sliderItems.add(SliderItem(R.drawable.img3))
        sliderItems.add(SliderItem(R.drawable.img4))
        sliderItems.add(SliderItem(R.drawable.img5))

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)
        viewPager2.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        val comPagTrans = CompositePageTransformer()
        comPagTrans.addTransformer(MarginPageTransformer(30))
        comPagTrans.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.75 + r * 0.15f).toFloat()
        }
        viewPager2.setPageTransformer(comPagTrans)
    }


}

