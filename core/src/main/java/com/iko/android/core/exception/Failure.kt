package com.iko.android.modularapp.exception

sealed class Failure {
    class NetworkConnection: Failure()
    class ServerError: Failure()
    class TokenExpiredError: Failure()
    class ServiceUnavailableError: Failure()
    class ServerUnavailableError: Failure()
}