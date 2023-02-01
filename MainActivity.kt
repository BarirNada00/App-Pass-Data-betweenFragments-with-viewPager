package ma.projet.android.frag

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), SendMessage {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                1 -> {
                    "TAB - 1"
                }
                2 -> {
                    "TAB - 2"
                }
                else -> {
                    throw Resources.NotFoundException("Position Not Found")
                }

            }
        }.attach()
    }

    override fun sendData(massage: String?) {
        val tag = "android:switcher:" + R.id.viewpager.toString() + ":" + 1
        val f = supportFragmentManager.findFragmentByTag(tag) as FragmentB?
        f!!.displayReceivedData(massage!!)
        var currentItem = getItem(+1)
        viewPager.currentItem = currentItem
    }

    fun getItem(i: Int) = viewPager.currentItem + i

}
