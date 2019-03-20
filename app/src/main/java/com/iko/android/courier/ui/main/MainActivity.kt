package com.iko.android.courier.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.ui.main.home.HomeFragment
import com.iko.android.courier.ui.profile.ProfileFragment
import com.iko.android.modularapp.base.CoreActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CoreActivity<MainVM>(MainVM::class.java, R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigation()
        showHomeFragment()
    }

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    private fun setupBottomNavigation() {
        bottom_navigation.selectedItemId = R.id.action_home
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            if (bottom_navigation.selectedItemId != item.itemId)
                when (item.itemId) {
                    R.id.action_home -> showHomeFragment()
                    R.id.action_cargo -> showFragment(HomeFragment(), HomeFragment::class.java.canonicalName)
                    R.id.action_delivers -> showFragment(HomeFragment(), HomeFragment::class.java.canonicalName)
                    R.id.action_profile -> showFragment(ProfileFragment(), ProfileFragment::class.java.canonicalName)
                }
            true
        }
    }

    private fun showFragment(fragment: Fragment?, tag: String) {
        val replaced = supportFragmentManager
            .beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.container, fragment!!, tag)
        try {
            replaced.commit()
        } catch (e: IllegalStateException) {
            replaced.commitAllowingStateLoss()
        }
    }

    private fun showHomeFragment() = showFragment(HomeFragment(), HomeFragment::class.java.canonicalName)

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.canonicalName)
        if (fragment == null || !fragment.isVisible) showHomeFragment()
        else super.onBackPressed()
    }

    companion object {

        fun start(activity: Activity?){
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            activity?.startActivity(intent)
        }

    }

}
