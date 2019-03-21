package com.iko.android.courier.ui.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.net.toUri
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.ProfileEvent
import com.iko.android.courier.ui.custom.ViewPagerAdapter
import com.iko.android.courier.ui.profile.balance.BalanceFragment
import com.iko.android.courier.ui.profile.review.ReviewFragment
import com.iko.android.courier.ui.splash.SplashActivity
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : CoreFragment<ProfileVM>(ProfileVM::class.java, R.layout.fragment_profile) {

    private val reviewFragment = ReviewFragment()
    private val balanceFragment = BalanceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
        setupViews()
    }

    private fun subscribeLiveData() {
        viewModel.event.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is ProfileEvent.ReviewListFetched -> {
                    rating.text = getString(R.string.label_ratings, it.rating)
                }
                is Event.Notification -> context?.showMessage(it.message)
            }
        })
    }

    private fun setupViews() {
        viewModel.currentUser?.let {
            toolbar.initForActivity(activity,  it.fullName)
            activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            phone_number.text = it.phoneNumber
            email.text = it.email
            it.avatarUrl?.let { avatar.setImageURI(it.toUri()) }
        }
        rating.text = getString(R.string.label_ratings, viewModel.rating)
        tabs.setupWithViewPager(viewpager)
        val adapter = ViewPagerAdapter(childFragmentManager!!)
        adapter.addFragment(reviewFragment, getString(R.string.label_reviews))
        adapter.addFragment(balanceFragment, getString(R.string.label_balance))
        viewpager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.exit) {
            viewModel.signOut()
            SplashActivity.start(context)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }

}