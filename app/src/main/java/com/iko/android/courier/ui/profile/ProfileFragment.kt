package com.iko.android.courier.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.ui.custom.ViewPagerAdapter
import com.iko.android.courier.ui.profile.balance.BalanceFragment
import com.iko.android.courier.ui.profile.review.ReviewFragment
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : CoreFragment<ProfileVM>(ProfileVM::class.java, R.layout.fragment_profile) {

    private val reviewFragment = ReviewFragment()
    private val balanceFragment = BalanceFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        setupViews(view)
        return view
    }

    private fun setupViews(view: View) {
        view.tabs.setupWithViewPager(view.viewpager)
        val adapter = ViewPagerAdapter(childFragmentManager!!)
        adapter.addFragment(reviewFragment, getString(R.string.label_reviews))
        adapter.addFragment(balanceFragment, getString(R.string.label_balance))
        view.viewpager.adapter = adapter
    }

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }

}