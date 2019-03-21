package com.iko.android.courier.ui.profile

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.ProfileEvent
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.ui.custom.ViewPagerAdapter
import com.iko.android.courier.ui.profile.balance.BalanceFragment
import com.iko.android.courier.ui.profile.review.ReviewFragment
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import org.parceler.Parcels

class ProfileFragment : CoreFragment<ProfileVM>(ProfileVM::class.java, R.layout.fragment_profile) {

    private val reviewFragment = ReviewFragment()
    private val balanceFragment = BalanceFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Parcels.unwrap<Person>(arguments?.getParcelable(CURRENT_USER))?.let {
            viewModel.currentUser = it
        }
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
            name.text = it.fullName
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

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }

    companion object {

        private const val CURRENT_USER = "CURRENT_USER"

        fun getInstance(person: Person): ProfileFragment {
            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putParcelable(CURRENT_USER, Parcels.wrap(person))
            fragment.arguments = bundle
            return fragment
        }

    }
}