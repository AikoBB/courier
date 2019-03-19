package com.iko.android.courier.domain

class Callback<S, F>(val onSuccess:(param: S) -> Unit, val onFailed: (param: F) -> Unit)