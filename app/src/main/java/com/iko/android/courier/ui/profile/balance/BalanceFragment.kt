package com.iko.android.courier.ui.profile.balance

import android.os.Bundle
import android.view.View
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_balance.*

class BalanceFragment : CoreFragment<BalanceVM>(BalanceVM::class.java, R.layout.fragment_balance) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        earnings.setOnClickListener { context?.showMessage(getString(R.string.warning_feature_later)) }
        expenses.setOnClickListener { context?.showMessage(getString(R.string.warning_feature_later)) }
    }

    override fun performDependencyInjection() {
        (activity.applicationContext as CourierApp).appComponent.inject(this)
    }
}