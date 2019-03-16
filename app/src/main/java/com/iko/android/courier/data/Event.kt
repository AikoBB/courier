package com.iko.android.courier.data

sealed class Event {
    class Notification(val message: String) : Event()
    class FinishActivity: Event()
}