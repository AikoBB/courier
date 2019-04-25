package com.iko.android.courier.data

import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.CargoList
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.database.entity.Review

sealed class Event {
    class Notification(val message: String) : Event()
    class FinishActivity: Event()
}

sealed class AuthEvent: Event(){
    class LoginSucceeded(val person: Person): AuthEvent()
    class LoginFailed(val description: String?): AuthEvent()
    class LoggedIn: AuthEvent()
    class NeedAuthorization: AuthEvent()
}

sealed class ProfileEvent: Event(){
    class ProfileFetched(val person: Person): ProfileEvent()
    class ReviewListFetched(val reviews: List<Review>, val rating: Float): ProfileEvent()
}

sealed class CargoEvent: Event(){
    class CargoUpdated(val cargo: Cargo): CargoEvent()
    class CargoRequestSent(val cargoId: String): CargoEvent()
    class CargoCreated(val cargo: Cargo): CargoEvent()
    class CargoDeleted(val cargoId: String): CargoEvent()
    class CargoListFetched(val cargoes: CargoList): CargoEvent()
    class CargoInfoFetched(val cargo: Cargo): CargoEvent()
    class CargoRequestAccepted(val cargo: Cargo): CargoEvent()
    class ProfileRequested(val person: Person): CargoEvent()
}