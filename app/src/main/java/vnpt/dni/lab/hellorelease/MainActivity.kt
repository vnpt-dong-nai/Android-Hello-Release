@file:Suppress("UNUSED_PARAMETER")

package vnpt.dni.lab.hellorelease

import android.annotation.SuppressLint
import android.app.Activity
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace.setCounter
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sp : SharedPreferences
    val colors = arrayOf(Color.BLACK, Color.RED, Color.GREEN, Color.BLUE)
    var curColorIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getSharedPreferences("DEMO_DATA", Activity.MODE_PRIVATE)

        showCounter(getCounter())
    }

    private fun showCounter(counter:Int) {
        Logger.d("show data")
        var text = "Hello Release" +
                "\n$packageName"

        if (BuildConfig.DEMO_FEATURE_CHANGE_COLOR_ON_TAP)
            text += "\nDemo feature: Color change on tap"

        text += "\nCounter: $counter"
        txtCounter.text = text
    }

    fun onUserClickThisTextView(v:View) {
        setCounter(getCounter()+1)
        if (BuildConfig.DEMO_FEATURE_CHANGE_COLOR_ON_TAP) {
            curColorIndex++
            if (curColorIndex >= colors.size) curColorIndex = 0
            txtCounter.setTextColor(colors[curColorIndex])
        }
    }

    private fun setCounter(counter: Int) {
        Logger.d("write data")
        val e = sp.edit()
        e.putInt("counter", counter)
        e.apply()

        showCounter(counter)
    }

    private fun getCounter(): Int {
        Logger.d("read saved data")
        return sp.getInt("counter", 0)
    }
}
