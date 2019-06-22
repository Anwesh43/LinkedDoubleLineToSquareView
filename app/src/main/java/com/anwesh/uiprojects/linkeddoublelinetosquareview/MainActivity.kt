package com.anwesh.uiprojects.linkeddoublelinetosquareview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.doublelinetosquareview.DoubleLineToSquareView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DoubleLineToSquareView.create(this)
    }
}
