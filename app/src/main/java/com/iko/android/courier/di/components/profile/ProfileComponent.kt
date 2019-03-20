package com.iko.android.courier.di.components.profile

import com.iko.android.courier.ui.profile.ProfileFragment
import com.iko.android.courier.ui.profile.balance.BalanceFragment
import com.iko.android.courier.ui.profile.review.ReviewFragment

interface ProfileComponent {
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: ReviewFragment)
    fun inject(fragment: BalanceFragment)
}