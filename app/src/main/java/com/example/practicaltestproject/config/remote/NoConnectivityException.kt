package com.example.practicaltestproject.config.remote

import java.io.IOException

object NoConnectivityException: IOException() {
    fun errorMsg(): String {
        return "No Internet Connection"
    }
}